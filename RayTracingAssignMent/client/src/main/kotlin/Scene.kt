import org.w3c.dom.HTMLCanvasElement
import org.khronos.webgl.WebGLRenderingContext as GL //# GL# we need this for the constants declared ˙HUN˙ a constansok miatt kell
import kotlin.math.*
import kotlin.js.Date
import vision.gears.webglmath.*

class Scene (
  val gl : WebGL2RenderingContext) {

  val vsQuad = Shader(gl, GL.VERTEX_SHADER, "quad-vs.glsl")
  val fsBackground = Shader(gl, GL.FRAGMENT_SHADER, "background-fs.glsl")
  val backgroundProgram = Program(gl, vsQuad, fsBackground)

  val texturedQuadGeometry = TexturedQuadGeometry(gl)

  val envTexture = TextureCube(gl,
    "media/posx512.jpg", "media/negx512.jpg",
    "media/posy512.jpg", "media/negy512.jpg",
    "media/posz512.jpg", "media/negz512.jpg"
  )

  val backgroundMaterial = Material(backgroundProgram)
  val backgroundMesh = Mesh(backgroundMaterial, texturedQuadGeometry)
  init{
    backgroundMaterial["envTexture"]?.set( this.envTexture )
  }
  val gameObjects = ArrayList<GameObject>()

  init {
    gameObjects += GameObject(backgroundMesh)
  }

  val quadrics = Array<Quadric>(16) { Quadric(it) }

  init{
    for (i in 0..15) {
      val random = Vec3();
      random.randomize(Vec3(-9.0f, -9.0f, -9.0f), Vec3 (9.0f, 9.0f, 9.0f))
      quadrics[i].surface.set(Quadric.unitSphere.clone())
      quadrics[i].surface.transform(Mat4().set().scale(3.0f, 3.0f, 3.0f).translate(random))
      quadrics[i].clipper.set(Quadric.unitSlab.clone())
      quadrics[i].clipper.transform(Mat4().set().scale(1.0f, 0.5f, 1.0f))
    }
  }


  val camera = PerspectiveCamera(*Program.all).apply{
    position.set(1f, 1f)
  }

  val timeAtFirstFrame = Date().getTime()
  var timeAtLastFrame =  timeAtFirstFrame

  fun resize(canvas : HTMLCanvasElement) {
    camera.setAspectRatio(canvas.width.toFloat() / canvas.height.toFloat())
    gl.viewport(0, 0, canvas.width, canvas.height)//#viewport# tell the rasterizer which part of the canvas to draw to ˙HUN˙ a raszterizáló ide rajzoljon
  }

  @Suppress("UNUSED_PARAMETER")
  fun update(keysPressed : Set<String>) {
    val timeAtThisFrame = Date().getTime()
    val dt = (timeAtThisFrame - timeAtLastFrame).toFloat() / 1000.0f
    val t = (timeAtThisFrame - timeAtFirstFrame).toFloat() / 1000.0f
    timeAtLastFrame = timeAtThisFrame

    gl.enable(GL.DEPTH_TEST)

    camera.move(dt, keysPressed)

    gl.clearColor(0.3f, 0.0f, 0.3f, 1.0f)//## red, green, blue, alpha in [0, 1]
    gl.clearDepth(1.0f)//## will be useful in 3D ˙HUN˙ 3D-ben lesz hasznos
    gl.clear(GL.COLOR_BUFFER_BIT or GL.DEPTH_BUFFER_BIT)//#or# bitwise OR of flags

    gl.enable(GL.BLEND)
    gl.blendFunc(
      GL.SRC_ALPHA,
      GL.ONE_MINUS_SRC_ALPHA)

    for (gameObject in gameObjects) {
      gameObject.move(dt, t, keysPressed, gameObjects)
      gameObject.update()
      gameObject.draw(camera, *quadrics)
    }
  }
}
