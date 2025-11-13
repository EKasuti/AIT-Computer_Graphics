import org.w3c.dom.HTMLCanvasElement
import org.khronos.webgl.WebGLRenderingContext as GL //# GL# we need this for the constants declared ˙HUN˙ a constansok miatt kell
import kotlin.math.*
import kotlin.js.Date
import vision.gears.webglmath.*

class Scene (
  val gl : WebGL2RenderingContext) {

  // Shaders
  val vsIdle = Shader(gl, GL.VERTEX_SHADER, "idle-vs.glsl")
  val fsSolid = Shader(gl, GL.FRAGMENT_SHADER, "solid-fs.glsl")
  val solidProgram = Program(gl, vsIdle, fsSolid, Program.PC)

  val vsTextured = Shader(gl, GL.VERTEX_SHADER, "textured-vs.glsl")
  val fsTextured = Shader(gl, GL.FRAGMENT_SHADER, "textured-fs.glsl")
  val texturedProgram = Program(gl, vsTextured, fsTextured, Program.PNT)

  val vsQuad = Shader(gl, GL.VERTEX_SHADER, "quad-vs.glsl")
  val fsBackground = Shader(gl, GL.FRAGMENT_SHADER, "background-fs.glsl")
  val backgroundProgram = Program(gl, vsQuad, fsBackground)

  val texturedQuadGeometry = TexturedQuadGeometry(gl)

  // Cube map
  val envTexture = TextureCube(gl,
    "media/posx512.jpg", "media/negx512.jpg",
    "media/posy512.jpg", "media/negy512.jpg",
    "media/posz512.jpg", "media/negz512.jpg"
  )

  val jsonLoader = JsonLoader()

  // Chevrolet car model
  val chevyMaterial = Material(texturedProgram).apply {
    gl.bindTexture(GL.TEXTURE_CUBE_MAP, null)
    this["colorTexture"]?.set(Texture2D(gl, "media/chevy/chevy.png"))
  }

  val carMeshes = jsonLoader.loadMeshes(
    gl, "media/chevy/chassis.json", chevyMaterial
  )

  val wheelMeshes = jsonLoader.loadMeshes(
    gl, "media/chevy/wheel.json", chevyMaterial
  )

  val backgroundMaterial = Material(backgroundProgram)
  val backgroundMesh = Mesh(backgroundMaterial, texturedQuadGeometry)

  init {
    backgroundMaterial["envTexture"]?.set(envTexture)
    val zoomUniform = backgroundMaterial["zoom"]

    if (zoomUniform != null) {
      zoomUniform.set(1.0f)
    }
  }

  // Car and wheels
  val wheelFL = Wheel(wheelMeshes, Vec3(+7.0f, -4f, +14f), true)
  val wheelFR = Wheel(wheelMeshes, Vec3(-7.0f, -4f, +14f), true)
  val wheelBL = Wheel(wheelMeshes, Vec3(+7.0f, -4f, -12f), false)
  val wheelBR = Wheel(wheelMeshes, Vec3(-7.0f, -4f, -12f), false)

  val car = Car(
    carMeshes,
    listOf(wheelFL, wheelFR, wheelBL, wheelBR)
  ). apply {
    position.set(0f, 0f, 0f)
    heading = (PI).toFloat()
  }

  val gameObjects = ArrayList<GameObject>().apply {
    add(GameObject(backgroundMesh))
    addAll(car.getObjects())
  }

  val camera = PerspectiveCamera(*Program.all).apply {
    position.set(0f, 12f, 100f)
  }

  // Zoom properties
  var currentZoom = 1.0f
  var targetZoom = 1.0f

  val timeAtFirstFrame = Date().getTime().toFloat()
  var timeAtLastFrame = timeAtFirstFrame

  fun resize(canvas : HTMLCanvasElement) {
    camera.setAspectRatio(canvas.width.toFloat() / canvas.height.toFloat())
    gl.viewport(0, 0, canvas.width, canvas.height)//#viewport# tell the rasterizer which part of the canvas to draw to ˙HUN˙ a raszterizáló ide rajzoljon
  }

  @Suppress("UNUSED_PARAMETER")
  fun update(keysPressed : Set<String>) {
    val timeAtThisFrame = Date().getTime().toFloat()
    val dt = (timeAtThisFrame - timeAtLastFrame).toFloat() / 1000.0f
    val t = (timeAtThisFrame - timeAtFirstFrame).toFloat() / 1000.0f
    timeAtLastFrame = timeAtThisFrame

    gl.enable(GL.DEPTH_TEST)

    camera.move(dt, keysPressed)

    val carSpeed = abs(car.speed)

    val speedFactor = (carSpeed * 0.015f).coerceIn(0f, 0.4f)
    targetZoom = 1.0f - speedFactor

    val zoomLerpSpeed = 3.0f
    val zoomDelta = (targetZoom - currentZoom) * dt * zoomLerpSpeed
    currentZoom += zoomDelta

    val zoomUniform = backgroundMaterial["zoom"]
    zoomUniform?.set(currentZoom)

    gl.clearColor(0.3f, 0.0f, 0.3f, 1.0f)
    gl.clearDepth(1.0f)
    gl.clear(GL.COLOR_BUFFER_BIT or GL.DEPTH_BUFFER_BIT)

    gl.enable(GL.BLEND)
    gl.blendFunc(
      GL.SRC_ALPHA,
    GL.ONE_MINUS_SRC_ALPHA)


    car.control(dt, keysPressed)
    car.updateHierarchy()

    for (gameObject in gameObjects) {
      gameObject.move(dt, t, keysPressed, gameObjects)
      gameObject.update()
      gameObject.draw(camera)
    }
  }
}
