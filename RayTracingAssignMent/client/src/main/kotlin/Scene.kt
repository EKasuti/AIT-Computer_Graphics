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
    // Light source (top right)
    backgroundMaterial["lightPositions[0]"]?.set( Vec3(15.0f, 20.0f, 5.0f) )
    backgroundMaterial["lightColors[0]"]?.set( Vec3(1.0f, 1.0f, 1.0f) )
    // Light source (bottom right)
    backgroundMaterial["lightPositions[1]"]?.set( Vec3(15.0f, 5.0f, 5.0f) )
    backgroundMaterial["lightColors[1]"]?.set( Vec3(1.0f, 1.0f, 1.0f) )
  }
  val gameObjects = ArrayList<GameObject>()

  init {
    gameObjects += GameObject(backgroundMesh)
  }

  val quadrics = Array<Quadric>(16) { Quadric(it) }

  init{
    // Light sphere (top right)
    quadrics[0].surface.set(Quadric.unitSphere.clone())
    quadrics[0].surface.transform(Mat4().set().scale(2.0f, 2.0f, 2.0f).translate(Vec3(15.0f, 20.0f, 5.0f)))
    quadrics[0].clipper.set(Quadric.unitSlab.clone())
    quadrics[0].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f))

    // SNOWMAN
    // Bottom sphere (largest)
    quadrics[1].surface.set(Quadric.unitSphere.clone())
    quadrics[1].surface.transform(Mat4().set().scale(2.5f, 2.5f, 2.5f).translate(Vec3(-10.0f, -3.0f, 0.0f)))
    quadrics[1].clipper.set(Quadric.unitSlab.clone())
    quadrics[1].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f))

    // Middle sphere
    quadrics[2].surface.set(Quadric.unitSphere.clone())
    quadrics[2].surface.transform(Mat4().set().scale(2.0f, 2.0f, 2.0f).translate(Vec3(-10.0f, 1.5f, 0.0f)))
    quadrics[2].clipper.set(Quadric.unitSlab.clone())
    quadrics[2].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f))

    // Head sphere (smallest)
    quadrics[3].surface.set(Quadric.unitSphere.clone())
    quadrics[3].surface.transform(Mat4().set().scale(1.5f, 1.5f, 1.5f).translate(Vec3(-10.0f, 5.0f, 0.0f)))
    quadrics[3].clipper.set(Quadric.unitSlab.clone())
    quadrics[3].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f))

    // Coal eyes (left)
    quadrics[4].surface.set(Quadric.unitSphere.clone())
    quadrics[4].surface.transform(Mat4().set().scale(0.3f, 0.3f, 0.3f).translate(Vec3(-10.5f, 5.5f, 1.2f)))
    quadrics[4].clipper.set(Quadric.unitSlab.clone())
    quadrics[4].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f))

    // Coal eyes (right)
    quadrics[5].surface.set(Quadric.unitSphere.clone())
    quadrics[5].surface.transform(Mat4().set().scale(0.3f, 0.3f, 0.3f).translate(Vec3(-9.5f, 5.5f, 1.2f)))
    quadrics[5].clipper.set(Quadric.unitSlab.clone())
    quadrics[5].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f))


    // PILE OF ORANGES
    // Orange 1
    quadrics[7].surface.set(Quadric.unitSphere.clone())
    quadrics[7].surface.transform(Mat4().set().scale(0.5f, 0.5f, 0.5f).translate(Vec3(-6.0f, -5.0f, 2.0f)))
    quadrics[7].clipper.set(Quadric.unitSlab.clone())
    quadrics[7].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f))

    // Orange 2
    quadrics[8].surface.set(Quadric.unitSphere.clone())
    quadrics[8].surface.transform(Mat4().set().scale(0.5f, 0.5f, 0.5f).translate(Vec3(-5.0f, -5.0f, 2.5f)))
    quadrics[8].clipper.set(Quadric.unitSlab.clone())
    quadrics[8].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f))

    // Orange 3
    quadrics[9].surface.set(Quadric.unitSphere.clone())
    quadrics[9].surface.transform(Mat4().set().scale(0.5f, 0.5f, 0.5f).translate(Vec3(-5.5f, -5.0f, 1.5f)))
    quadrics[9].clipper.set(Quadric.unitSlab.clone())
    quadrics[9].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f))

    // Orange 4 (Top)
    quadrics[10].surface.set(Quadric.unitSphere.clone())
    quadrics[10].surface.transform(Mat4().set().scale(0.5f, 0.5f, 0.5f).translate(Vec3(-5.5f, -4.2f, 2.0f)))
    quadrics[10].clipper.set(Quadric.unitSlab.clone())
    quadrics[10].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f))

    // Silver Baubles
    // Bauble 1
    quadrics[11].surface.set(Quadric.unitSphere.clone())
    quadrics[11].surface.transform(Mat4().set().scale(0.8f, 0.8f, 0.8f).translate(Vec3(-2.0f, -4.0f, 2.0f)))
    quadrics[11].clipper.set(Quadric.unitSlab.clone())
    quadrics[11].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f))

    // Bauble 2
    quadrics[12].surface.set(Quadric.unitSphere.clone())
    quadrics[12].surface.transform(Mat4().set().scale(0.8f, 0.8f, 0.8f).translate(Vec3(-1.0f, -3.0f, 0.0f)))
    quadrics[12].clipper.set(Quadric.unitSlab.clone())
    quadrics[12].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f))

    // Golden Bells
    // Bell 1
    quadrics[13].surface.set(Quadric.unitSphere.clone())
    quadrics[13].surface.transform(Mat4().set().scale(0.8f, 0.8f, 0.8f).translate(Vec3(2.0f, -4.0f, 2.0f)))
    quadrics[13].clipper.set(Quadric.unitSlab.clone())
    quadrics[13].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f))

    // Bell 2
    quadrics[14].surface.set(Quadric.unitSphere.clone())
    quadrics[14].surface.transform(Mat4().set().scale(0.8f, 0.8f, 0.8f).translate(Vec3(3.0f, -3.0f, 0.0f)))
    quadrics[14].clipper.set(Quadric.unitSlab.clone())
    quadrics[14].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f))
  }

  val camera = PerspectiveCamera(*Program.all).apply{
    position.set(0f, 5f, 40f)
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
