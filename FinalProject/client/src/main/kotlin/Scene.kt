import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLElement
import kotlinx.browser.document
import org.khronos.webgl.WebGLRenderingContext as GL //# GL# we need this for the constants declared ˙HUN˙ a constansok miatt kell
import kotlin.js.Date
import vision.gears.webglmath.UniformProvider
import vision.gears.webglmath.Vec1
import vision.gears.webglmath.Vec2
import vision.gears.webglmath.Vec3
import vision.gears.webglmath.Mat4
import kotlin.math.*
import kotlin.random.Random

class Scene (
  val gl : WebGL2RenderingContext,
  val overlay : org.w3c.dom.HTMLDivElement)  : UniformProvider("scene") {

  val time by Vec1()  // Scene time uniform for shaders

  val texturedQuadGeometry = TexturedQuadGeometry(gl)
  val vsTextured = Shader(gl, GL.VERTEX_SHADER, "textured-vs.glsl")
  val fsTextured = Shader(gl, GL.FRAGMENT_SHADER, "textured-fs.glsl")
  val fsTexturedLit = Shader(gl, GL.FRAGMENT_SHADER, "textured-lit-fs.glsl")
  val texturedProgram = Program(gl, vsTextured, fsTextured)
  val texturedLitProgram = Program(gl, vsTextured, fsTexturedLit)

  val asteroidMaterial = Material(texturedProgram).apply{
    this["colorTexture"]?.set(Texture2D(gl, "media/asteroid.png"))
    this["textureScale"]?.set(1f/6f, 1f/6f)
  }
  val asteroidMesh = Mesh(asteroidMaterial, texturedQuadGeometry)

  val vsBackground = Shader(gl, GL.VERTEX_SHADER, "background-vs.glsl")  
  val backgroundProgram = Program(gl, vsBackground, fsTextured)
  
  // Dynamic lighting arrays
  val lightPositions = Array(8) { Vec3() }
  val lightPowerDensities = Array(8) { Vec3() }
  var numActiveLights = 0

  //TODO: create various materials with different solidColor settings
  val fighterMaterial = Material(texturedLitProgram).apply{
    this["colorTexture"]?.set(Texture2D(gl, "media/fighter.png"))
  }
  val backgroundMaterial = Material(backgroundProgram).apply{
    this["colorTexture"]?.set(Texture2D(gl, "media/nebula.jpg"))
  }
  val explosionMaterial = Material(texturedLitProgram).apply {
    this["colorTexture"]?.set(Texture2D(gl, "media/explosion.png"))
  }
  val flameMaterial = Material(texturedLitProgram).apply {
    this["colorTexture"]?.set(Texture2D(gl, "media/flame.png"))
  }
  val bulletMaterial = Material(texturedLitProgram).apply {
    this["colorTexture"]?.set(Texture2D(gl, "media/bullet.png"))
  }
  val seekerMaterial = Material(texturedLitProgram).apply {
    this["colorTexture"]?.set(Texture2D(gl, "media/ufo.png"))
  }
  val diamondMaterial = Material(texturedLitProgram).apply {
    this["colorTexture"]?.set(Texture2D(gl, "media/diamond.png"))
  }

  // Particle materials (using flame texture for particles)
  val particleMaterial = Material(texturedLitProgram).apply {
    this["colorTexture"]?.set(Texture2D(gl, "media/flame.png"))
  }

  val backgroundMesh = Mesh(backgroundMaterial, texturedQuadGeometry)
  val fighterMesh = Mesh(fighterMaterial, texturedQuadGeometry)
  val explosionMesh = Mesh(explosionMaterial, texturedQuadGeometry)
  val flameMesh = Mesh(flameMaterial, texturedQuadGeometry)
  val bulletMesh = Mesh(bulletMaterial, texturedQuadGeometry)
  val seekerMesh = Mesh(seekerMaterial, texturedQuadGeometry)
  val diamondMesh = Mesh(diamondMaterial, texturedQuadGeometry)
  val particleMesh = Mesh(particleMaterial, texturedQuadGeometry)

  val camera = OrthoCamera().apply{
    position.set(1f, 1f)
    windowSize.set(20f, 20f)
    updateViewProjMatrix()
  }

  var gameObjects = ArrayList<GameObject>()
  var flame: FlameGameObject? = null
   // Seeker
  var seekerSpawnTimer = 0f
  val seekerSpawnInterval = 5f
  // diamond collection
  val collectedDiamonds = mutableListOf<GameObject>()

  // Particle emitter for avatar exhaust
  val exhaustEmitter = ParticleEmitter(
    mesh = particleMesh,
    emissionRate = 50f,
    particleLifetime = 0.6f,
    particleColor = Vec3(1f, 0.6f, 0.2f), // Orange exhaust
    spreadAngle = 0.8f,
    initialSpeed = 4f
  )

  // Camera shake effect
  var cameraShakeIntensity = 0f
  val cameraShakeDecay = 5f
  val cameraShakeOffset = Vec3()

  fun addCameraShake(intensity: Float) {
    cameraShakeIntensity = maxOf(cameraShakeIntensity, intensity)
  }

  // Score and combo system
  var score = 0
  var comboCount = 0
  var comboTimer = 0f
  val comboTimeout = 3f  // Combo resets after 3 seconds

  fun addScore(points: Int) {
    val multiplier = 1 + comboCount
    score += points * multiplier
  }

  fun addCombo() {
    comboCount++
    comboTimer = comboTimeout
  }

  fun resetCombo() {
    comboCount = 0
    comboTimer = 0f
  }

  // Health and lives system
  var lives = 3
  var health = 3
  val maxHealth = 3
  var invincibilityTimer = 0f
  val invincibilityDuration = 2f

  // Slow-motion effect
  var slowMoTimer = 0f
  val slowMoDuration = 0.5f
  val slowMoFactor = 0.3f  // 30% speed

  fun triggerSlowMo() {
    slowMoTimer = slowMoDuration
  }

  fun takeDamage(amount: Int = 1) {
    if (invincibilityTimer > 0f) return  // Invincible

    health -= amount
    invincibilityTimer = invincibilityDuration
    addCameraShake(0.5f)

    if (health <= 0) {
      lives--
      if (lives > 0) {
        health = maxHealth  // Respawn with full health
        console.log("Life lost! Lives remaining: $lives")
      } else {
        console.log("Game Over! Final Score: $score")
      }
    }
  }

  val avatar = object : GameObject(fighterMesh) {
    val velocity = Vec3()
    var angularVelocity = 0f
    var shootCooldown = 0f

    override fun move(
      dt : Float,
      t : Float,
      keysPressed : Set<String>,
      gameObjects : List<GameObject>
    ) : Boolean {

      // asteroid collision
      gameObjects.forEach{
        if(it == this){ 
          return@forEach
        }
        if(it is AsteroidGameObject){
          val diff = position - it.position
          val dist = diff.length()
          if(dist < 2.0f){
            val relativeVelocity =
              velocity - it.velocity
            val normal = diff
            normal.normalize()
            position += normal * 0.01f
            val relativeVelocityAlongNormal =
              normal * relativeVelocity.dot(normal)
            velocity -= relativeVelocityAlongNormal * 0.5f * 1.6f
            it.velocity += relativeVelocityAlongNormal * 0.5f * 1.6f

            // Explosion effect
            val explosion = ExplosionGameObject(explosionMesh).apply {
              position.set(it.position)
              scale.set(2.5f, 2.5f, 1.0f)
            }
            (gameObjects as ArrayList).add(explosion)

            // Emit explosion particles
            exhaustEmitter.emitBurst(
              count = 30,
              position = Vec3(it.position),
              direction = (position - it.position).apply { normalize() }
            )

            // Camera shake on collision
            addCameraShake(0.3f)
            //return@move false
          }
        }
      }

      var thrusting = false
      if("W" in keysPressed){
        val ahead = Vec3(cos(roll), sin(roll), 0f)
        velocity += ahead * 10.0f * dt
        thrusting = true
      }
      if("S" in keysPressed){
        val backward = Vec3(-cos(roll), -sin(roll), 0f)
        velocity += backward * 10.0f * dt
        thrusting = false
      }
      if("D" in keysPressed){
        angularVelocity -= 3.0f * dt       
      }
      if("A" in keysPressed){
        angularVelocity += 3.0f * dt
      }      
      if("X" in keysPressed){
        return false
      }
      
      // Bullet - Spread Shot (3 bullets)
      shootCooldown -= dt
      if("SPACE" in keysPressed && shootCooldown <= 0f){
        // Shoot 3 bullets in a spread pattern
        val spreadAngles = listOf(-0.15f, 0f, 0.15f)  // -8.6°, 0°, +8.6°
        spreadAngles.forEach { angleOffset ->
          val bulletAngle = roll + angleOffset
          val bulletDirection = Vec3(cos(bulletAngle), sin(bulletAngle), 0f)
          val bullet = BulletGameObject(bulletMesh, this, bulletDirection)
          (gameObjects as ArrayList).add(bullet)
        }
        shootCooldown = 0.3f  // Faster shooting with spread
      }
      position += velocity * dt
      roll += angularVelocity * dt

      velocity *= 0.98f.pow(dt)
      angularVelocity *= 0.98f.pow(dt)

      // Landing collision detection
      gameObjects.forEach {
        if (it is PlatformGameObject) {
          val platformY = it.yLevel
          val distanceToPlatform = position.y - platformY

          if (distanceToPlatform < 1.0f) {
            val penetration = 1.0f - distanceToPlatform
            val springStrength = 20.0f

            velocity.y += springStrength * penetration * dt

            velocity.y *= 0.95f.pow(dt)

            if (position.y < platformY + 1.0f) {
              position.y = platformY + 1.0f
            }
          }
        }
      }

      // Flame visibility
      gameObjects.filterIsInstance<FlameGameObject>().forEach {
        it.isActive = thrusting
      }

      // Control exhaust particles
      gameObjects.filterIsInstance<ParticleEmitter>().forEach { emitter ->
        // Position emitter at back of ship
        val backOffset = Vec3(-cos(roll) * 2.5f, -sin(roll) * 2.5f, 0f)
        emitter.position.set(position + backOffset)
        emitter.emissionDirection.set(-cos(roll), -sin(roll), 0f)
        emitter.isEmitting = thrusting
      }

      return true
    }
  }
  init {
    gameObjects += GameObject(backgroundMesh)
    gameObjects += avatar
    avatar.roll = 1f

    // Flames
    val leftFlame = FlameGameObject(flameMesh, avatar, Vec3(-2.6f, -0.4f, -1.0f))
    val rightFlame = FlameGameObject(flameMesh, avatar, Vec3(-2.6f, 0.4f, -1.0f))
    gameObjects += leftFlame
    gameObjects += rightFlame
    // Add exhaust particle emitter
    gameObjects += exhaustEmitter

    // Seeker enemy
    val seeker = SeekerGameObject(seekerMesh, avatar, { takeDamage() }).apply {
      position.set(10.0f, 0.0f, 0.0f)
      scale.set(0.8f, 0.8f, 1.0f)
    }
    gameObjects += seeker

    // Platform
    val platformMaterial = Material(texturedProgram).apply {
      this["colorTexture"]?.set(Texture2D(gl, "media/platform.png"))
    }

    val platformMesh = Mesh(platformMaterial, texturedQuadGeometry)
    // Single platform
    // val platform = PlatformGameObject(platformMesh, yLevel = -5.0f)
    // gameObjects += platform

    // Multiple platforms
    for (i in -3..3) {
      val platform = PlatformGameObject(platformMesh, yLevel = -9.0f).apply {
        position.set(i * 6.0f, -9.0f, 0.0f)
      }
      gameObjects += platform
    }

    val asteroid1 = AsteroidGameObject(asteroidMesh).apply {
      position.set(-5.0f, 0.0f, 0.0f)
      scale.set(1.0f, 1.0f, 1.0f)
      velocity.set(0.2f)
    }
  
    val asteroid2 = AsteroidGameObject(asteroidMesh).apply {
      position.set(5.0f, 5.0f, 0.0f)
      velocity.set(-0.2f)
    }
  
    gameObjects += asteroid1
    gameObjects += asteroid2
  }

  fun resize(canvas : HTMLCanvasElement) {
    gl.viewport(0, 0, canvas.width, canvas.height)//#viewport# tell the rasterizer which part of the canvas to draw to ˙HUN˙ a raszterizáló ide rajzoljon
    camera.setAspectRatio(canvas.width.toFloat()/canvas.height)
  }

  val timeAtFirstFrame = Date().getTime()
  var timeAtLastFrame =  timeAtFirstFrame

  init {
    addComponentsAndGatherUniforms(texturedProgram, texturedLitProgram, backgroundProgram)
  }

  @Suppress("UNUSED_PARAMETER")
  fun update(keysPressed : Set<String>) {
    val timeAtThisFrame = Date().getTime()
    var dt = (timeAtThisFrame - timeAtLastFrame).toFloat() / 1000.0f
    val realDt = dt  // Store real dt for slow-mo timer
    val t = (timeAtThisFrame - timeAtFirstFrame).toFloat() / 1000.0f
    time.set(t) // Set scene time uniform
    timeAtLastFrame = timeAtThisFrame

    // Apply slow-motion effect
    if (slowMoTimer > 0f) {
      dt *= slowMoFactor
      slowMoTimer -= realDt
    }

    // Spawn seekers
    seekerSpawnTimer -= dt
    if (seekerSpawnTimer <= 0f) {
      spawnSeeker()
      seekerSpawnTimer = seekerSpawnInterval
    }

    // Spawn diamonds
    if (Random.nextFloat() < 0.02) {
      spawnDiamond()
    }

    // Update camera shake
    if (cameraShakeIntensity > 0f) {
      cameraShakeOffset.set(
        (Random.nextFloat() - 0.5f) * cameraShakeIntensity,
        (Random.nextFloat() - 0.5f) * cameraShakeIntensity,
        0f
      )
      cameraShakeIntensity -= cameraShakeDecay * dt
      if (cameraShakeIntensity < 0f) {
        cameraShakeIntensity = 0f
        cameraShakeOffset.set(0f, 0f, 0f)
      }
    }

    // Apply camera position with shake
    camera.position.set(avatar.position + cameraShakeOffset)
    camera.updateViewProjMatrix()

    gl.clearColor(0.3f, 0.0f, 0.3f, 1.0f)//## red, green, blue, alpha in [0, 1]
    gl.clearDepth(1.0f)//## will be useful in 3D ˙HUN˙ 3D-ben lesz hasznos
    gl.clear(GL.COLOR_BUFFER_BIT or GL.DEPTH_BUFFER_BIT)//#or# bitwise OR of flags

    gl.enable(GL.BLEND)
    gl.blendFunc(
      GL.SRC_ALPHA,
      GL.ONE_MINUS_SRC_ALPHA)

    val deathRow = ArrayList<GameObject>()
    gameObjects.forEach{
      if(it is DiamondGameObject) {
        val toAvatar = avatar.position - it.position
        if (toAvatar.length() < 1.2f && !it.collected) {
          it.collected = true
          collectedDiamonds += it
          addScore(10)  // 10 points per diamond
          console.log("Diamond collected! Total: ${collectedDiamonds.size}, Score: $score")
        }
      }
      if(!it.move(dt, t, keysPressed, gameObjects)){
        deathRow += it
      }
    }

    // Check bullet-seeker collisions
    val bulletsToRemove = ArrayList<BulletGameObject>()
    val seekersToRemove = ArrayList<SeekerGameObject>()

    gameObjects.filterIsInstance<BulletGameObject>().forEach { bullet ->
      gameObjects.filterIsInstance<SeekerGameObject>().forEach { seeker ->
        val diff = bullet.position - seeker.position
        if (diff.length() < 1.5f) {
          bulletsToRemove += bullet
          seekersToRemove += seeker
        }
      }
    }

    // Remove hit bullets and seekers, award score/combo
    bulletsToRemove.forEach {
      deathRow += it
    }
    seekersToRemove.forEach { seeker ->
      deathRow += seeker
      addScore(100) // 100 points per seeker kill
      addCombo() // Increase combo
      triggerSlowMo() // Dramatic slow-motion effect
      console.log("Seeker killed! Score: $score, Combo: ${comboCount}x")

      // Create explosion at seeker position
      val explosion = ExplosionGameObject(explosionMesh).apply {
        position.set(seeker.position)
        scale.set(2.5f, 2.5f, 1.0f)
      }
      (gameObjects as ArrayList).add(explosion)
      addCameraShake(0.2f)
    }

    // Update combo timer
    if (comboCount > 0) {
      comboTimer -= dt
      if (comboTimer <= 0f) {
        resetCombo()
        console.log("Combo reset")
      }
    }

    // Update invincibility timer
    if (invincibilityTimer > 0f) {
      invincibilityTimer -= dt
      // Flash avatar when invincible (toggle visibility)
      val flashRate = 0.1f
      val shouldShow = ((invincibilityTimer / flashRate).toInt() % 2) == 0
      avatar.scale.set(if (shouldShow) 1f else 0.8f, if (shouldShow) 1f else 0.8f, 1f)
    } else {
      avatar.scale.set(1f, 1f, 1f)
    }

    deathRow.forEach{
      gameObjects -= it
    }
    gameObjects.forEach{
      it.update()
    }

    // Gather dynamic lights from game objects
    numActiveLights = 0
    gameObjects.forEach { obj ->
      if (numActiveLights < 8) {
        when (obj) {
          is BulletGameObject -> {
            lightPositions[numActiveLights].set(obj.position.x, obj.position.y, obj.position.z)
            lightPowerDensities[numActiveLights].set(2.0f, 2.0f, 3.0f) // Bright white-blue
            numActiveLights++
          }
          is ExplosionGameObject -> {
            lightPositions[numActiveLights].set(obj.position.x, obj.position.y, obj.position.z)
            lightPowerDensities[numActiveLights].set(8.0f, 4.0f, 1.0f) // Bright orange
            numActiveLights++
          }
          is SeekerGameObject -> {
            lightPositions[numActiveLights].set(obj.position.x, obj.position.y, obj.position.z)
            lightPowerDensities[numActiveLights].set(3.0f, 0.3f, 0.3f) // Red menacing glow
            numActiveLights++
          }
          is DiamondGameObject -> {
            if (!obj.collected) {
              lightPositions[numActiveLights].set(obj.position.x, obj.position.y, obj.position.z)
              lightPowerDensities[numActiveLights].set(3.0f, 2.5f, 0.5f) // Golden glow
              numActiveLights++
            }
          }
        }
      }
    }

    // Update lighting uniforms for the lit program
    texturedLitProgram.gl.useProgram(texturedLitProgram.glProgram)
    for (i in 0 until numActiveLights) {
      val posLoc = gl.getUniformLocation(texturedLitProgram.glProgram, "lights.position[$i]")
      gl.uniform4f(posLoc, lightPositions[i].x, lightPositions[i].y, lightPositions[i].z, 1.0f)

      val powerLoc = gl.getUniformLocation(texturedLitProgram.glProgram, "lights.powerDensity[$i]")
      gl.uniform3f(powerLoc, lightPowerDensities[i].x, lightPowerDensities[i].y, lightPowerDensities[i].z)
    }
    val numLightsLoc = gl.getUniformLocation(texturedLitProgram.glProgram, "lights.numLights")
    gl.uniform1i(numLightsLoc, numActiveLights)

    gameObjects.forEach{
      if (it is ParticleEmitter) {
        it.drawParticles(this, camera)
      } else {
        it.draw(this, camera)
      }
    }

    val cornerX = camera.position.x + camera.windowSize.x / 2f - 1.0f
    val cornerY = camera.position.y + camera.windowSize.y / 2f - 1.0f

    collectedDiamonds.forEachIndexed { i, _ ->
      val displayDiamond = GameObject(diamondMesh).apply {
        // we then offset each diamond by 1 unit to the left
        position.set(cornerX - i * 1.0f, cornerY, 0f)
        scale.set(0.5f, 0.5f, 1.0f)
      }
      displayDiamond.update()
      displayDiamond.draw(this, camera)
    }

    // Update UI elements
    (document.getElementById("score") as? HTMLElement)?.textContent = score.toString()
    (document.getElementById("lives") as? HTMLElement)?.textContent = lives.toString()
    (document.getElementById("health") as? HTMLElement)?.textContent = health.toString()
    (document.getElementById("combo") as? HTMLElement)?.textContent = comboCount.toString()

    // Show/hide combo display
    val comboDisplay = document.getElementById("combo-display") as? HTMLElement
    comboDisplay?.style?.display = if (comboCount > 0) "block" else "none"

    // Show game over screen if out of lives
    if (lives <= 0) {
      (document.getElementById("final-score") as? HTMLElement)?.textContent = score.toString()
      (document.getElementById("game-over") as? HTMLElement)?.style?.display = "block"
    }
  }

  // Random spawn near the camera
  fun spawnSeeker() {
    val spawnX = camera.position.x + (-10..10).random().toFloat()
    val spawnY = camera.position.y + (-6..6).random().toFloat()

    val seeker = SeekerGameObject(seekerMesh, avatar, { takeDamage() }).apply {
      position.set(spawnX, spawnY, 0f)
      scale.set(0.8f, 0.8f, 1.0f)
    }

    gameObjects += seeker
  }

  // Random spawn of diamond above the avatar
  fun spawnDiamond() {
    val spawnX = avatar.position.x + (-8..8).random().toFloat()
    val spawnY = avatar.position.y + (8..12).random().toFloat()
    val diamond = DiamondGameObject(diamondMesh, avatar).apply {
      position.set(spawnX, spawnY, 0f)
      scale.set(0.5f, 0.5f, 1.0f)
    }
    gameObjects += diamond
  }
}
