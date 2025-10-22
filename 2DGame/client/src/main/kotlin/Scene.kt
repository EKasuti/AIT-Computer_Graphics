import org.w3c.dom.HTMLCanvasElement
import org.khronos.webgl.WebGLRenderingContext as GL //# GL# we need this for the constants declared ˙HUN˙ a constansok miatt kell
import kotlin.js.Date
import vision.gears.webglmath.UniformProvider
import vision.gears.webglmath.Vec1
import vision.gears.webglmath.Vec2
import vision.gears.webglmath.Vec3
import vision.gears.webglmath.Mat4
import kotlin.math.*

class Scene (
  val gl : WebGL2RenderingContext)  : UniformProvider("scene") {

  val texturedQuadGeometry = TexturedQuadGeometry(gl)


  val vsTextured = Shader(gl, GL.VERTEX_SHADER, "textured-vs.glsl")
  val fsTextured = Shader(gl, GL.FRAGMENT_SHADER, "textured-fs.glsl")
  val texturedProgram = Program(gl, vsTextured, fsTextured  )
  val asteroidMaterial = Material(texturedProgram).apply{
    this["colorTexture"]?.set(Texture2D(gl, "media/asteroid.png"))
    this["textureScale"]?.set(1f/6f, 1f/6f)
  }
  val asteroidMesh = Mesh(asteroidMaterial, texturedQuadGeometry)

  val vsBackground = Shader(gl, GL.VERTEX_SHADER, "background-vs.glsl")  
  val backgroundProgram = Program(gl, vsBackground, fsTextured)
  
  //TODO: create various materials with different solidColor settings
  val fighterMaterial = Material(texturedProgram).apply{
    this["colorTexture"]?.set(Texture2D(gl, "media/fighter.png"))
  }
  val backgroundMaterial = Material(backgroundProgram).apply{
    this["colorTexture"]?.set(Texture2D(gl, "media/nebula.jpg"))
  }
  
  val backgroundMesh = Mesh(backgroundMaterial, texturedQuadGeometry)
  val fighterMesh = Mesh(fighterMaterial, texturedQuadGeometry)

  val camera = OrthoCamera().apply{
    position.set(1f, 1f)
    windowSize.set(20f, 20f)
    updateViewProjMatrix()
  }

  var gameObjects = ArrayList<GameObject>()

  val avatar = object : GameObject(fighterMesh) {
    val velocity = Vec3()
    var angularVelocity = 0f
    override fun move(
      dt : Float,
      t : Float,
      keysPressed : Set<String>,
      gameObjects : List<GameObject>
    ) : Boolean {

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
            //return@move false
          }
        }
      }

      if("W" in keysPressed){
        val ahead = Vec3(cos(roll), sin(roll), 0f)
        velocity += ahead * 10.0f * dt
      }
      if("S" in keysPressed){
        val backward = Vec3(-cos(roll), -sin(roll), 0f)
        velocity += backward * 10.0f * dt
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
      
      position += velocity * dt
      roll += angularVelocity * dt

      velocity *= 0.98f.pow(dt)
      angularVelocity *= 0.98f.pow(dt)

      return true
    }
  }
  init {
    gameObjects += GameObject(backgroundMesh)
    gameObjects += avatar
    avatar.roll = 1f
  }

  val asteroid = AsteroidGameObject(asteroidMesh).apply {
    position.set(-5.0f, 0.0f, 0.0f)
    scale.set(1.0f, 1.0f, 1.0f)
    velocity.set(0.2f)
  }
  init {
    gameObjects += asteroid
  }

  val asteroid2 = AsteroidGameObject(asteroidMesh).apply {
    position.set(5.0f, 5.0f, 0.0f)
    velocity.set(-0.2f)
  }
  init {
    gameObjects += asteroid2
  }

  fun resize(canvas : HTMLCanvasElement) {
    gl.viewport(0, 0, canvas.width, canvas.height)//#viewport# tell the rasterizer which part of the canvas to draw to ˙HUN˙ a raszterizáló ide rajzoljon
    camera.setAspectRatio(canvas.width.toFloat()/canvas.height)
  }

  val timeAtFirstFrame = Date().getTime()
  var timeAtLastFrame =  timeAtFirstFrame
  //TODO: add property reflecting uniform scene.time
  //TODO: add all programs as child components

  @Suppress("UNUSED_PARAMETER")
  fun update(keysPressed : Set<String>) {
    val timeAtThisFrame = Date().getTime() 
    val dt = (timeAtThisFrame - timeAtLastFrame).toFloat() / 1000.0f
    val t = (timeAtThisFrame - timeAtFirstFrame).toFloat() / 1000.0f
    //TODO: set property time (reflecting uniform scene.time) 
    timeAtLastFrame = timeAtThisFrame
    
    camera.position.set(avatar.position)
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
      if(!it.move(dt, t, keysPressed, gameObjects)){
        deathRow += it
      }
    }
    deathRow.forEach{
      gameObjects -= it
    }
    gameObjects.forEach{
      it.update()
    }
    gameObjects.forEach{
      it.draw(this, camera)
    }
  }
}
