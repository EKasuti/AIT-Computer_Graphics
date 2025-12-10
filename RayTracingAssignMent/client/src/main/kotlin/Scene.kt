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
    // Light source (Candle)
    backgroundMaterial["lightPositions[2]"]?.set( Vec3(-2.0f, -2.8f, 4.0f) )
    backgroundMaterial["lightColors[2]"]?.set( Vec3(1.0f, 0.6f, 0.2f) )
  }
  val gameObjects = ArrayList<GameObject>()

  init {
    gameObjects += GameObject(backgroundMesh)
  }

  val quadrics = Array<Quadric>(32) { Quadric(it) }
  
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

    // FIR TREE
    // Trunk (brown cylinder)
    quadrics[15].surface.set(Quadric.unitCylinder.clone())
    quadrics[15].surface.transform(Mat4().set().scale(0.5f, 5.0f, 0.5f).translate(Vec3(5.0f, -2.0f, 0.0f)))
    quadrics[15].clipper.set(Quadric.unitClipper.clone())
    quadrics[15].clipper.transform(Mat4().set().scale(1.0f, 5.0f, 1.0f).translate(Vec3(5.0f, -2.0f, 0.0f)))

    // Bottom cone layer (largest)
    quadrics[16].surface.set(Quadric.cone.clone())
    quadrics[16].surface.transform(Mat4().set().scale(2.0f, 2.0f, 2.0f).translate(Vec3(5.0f, 1.5f, 0.0f)))
    quadrics[16].clipper.set(Quadric.unitClipper.clone())
    quadrics[16].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f).translate(Vec3(0.0f, -1.0f, 0.0f)))
    quadrics[16].clipper.transform(Mat4().set().scale(2.0f, 2.0f, 2.0f).translate(Vec3(5.0f, 1.5f, 0.0f)))

    // Middle cone layer
    quadrics[17].surface.set(Quadric.cone.clone())
    quadrics[17].surface.transform(Mat4().set().scale(1.5f, 1.5f, 1.5f).translate(Vec3(5.0f, 3.0f, 0.0f)))
    quadrics[17].clipper.set(Quadric.unitClipper.clone())
    quadrics[17].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f).translate(Vec3(0.0f, -1.0f, 0.0f)))
    quadrics[17].clipper.transform(Mat4().set().scale(1.5f, 1.5f, 1.5f).translate(Vec3(5.0f, 3.0f, 0.0f)))

    // Top cone layer (smallest)
    quadrics[18].surface.set(Quadric.cone.clone())
    quadrics[18].surface.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f).translate(Vec3(5.0f, 4.5f, 0.0f)))
    quadrics[18].clipper.set(Quadric.unitClipper.clone())
    quadrics[18].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f).translate(Vec3(0.0f, -1.0f, 0.0f)))
    quadrics[18].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f).translate(Vec3(5.0f, 4.5f, 0.0f)))

    // CANDLE (Wax + Flame)
    // Wax (Cylinder) Index 29
    quadrics[29].surface.set(Quadric.unitCylinder.clone())
    quadrics[29].surface.transform(Mat4().scale(0.3f, 1.0f, 0.3f).translate(Vec3(-2.0f, -4.0f, 4.0f)))
    quadrics[29].clipper.set(Quadric.unitClipper.clone())
    quadrics[29].clipper.transform(Mat4().scale(1.0f, 1.0f, 1.0f).translate(Vec3(-2.0f, -4.0f, 4.0f)))

    // Flame (Sphere) Index 30
    quadrics[30].surface.set(Quadric.unitSphere.clone())
    quadrics[30].surface.transform(Mat4().scale(0.15f, 0.25f, 0.15f).translate(Vec3(-2.0f, -2.8f, 4.0f)))
    quadrics[30].clipper.set(Quadric.unitSlab.clone())
    quadrics[30].clipper.transform(Mat4().scale(1.0f, 1.0f, 1.0f)) // No clipping needed really

    // Wooden Floor (Huge sphere acting as a plane)
    quadrics[19].surface.set(Quadric.unitSphere.clone())
    quadrics[19].surface.transform(Mat4().set().scale(200.0f, 200.0f, 200.0f).translate(Vec3(0.0f, -205.0f, 0.0f)))
    quadrics[19].clipper.set(Quadric.unitSlab.clone())
    quadrics[19].clipper.transform(Mat4().set().scale(1.0f, 1.0f, 1.0f))

    // PRESENTS (Orthogonal Box Slabs)
    // Red Box (indices 20-22)
    val redBoxM = Mat4().scale(0.7f, 0.7f, 0.7f).translate(Vec3(3.5f, -4.3f, 1.5f))
    
    // Y-Slab (Top/Bottom)
    quadrics[20].surface.set(Quadric.unitSlab.clone())
    quadrics[20].surface.transform(redBoxM)
    quadrics[20].clipper.set(Quadric.unitClipper.clone())
    quadrics[20].clipper.transform(Mat4().rotate(1.5708f, Vec3(0.0f, 0.0f, 1.0f)))
    quadrics[20].clipper.transform(redBoxM)
    quadrics[20].clipper2.set(Quadric.unitClipper.clone())
    quadrics[20].clipper2.transform(Mat4().rotate(1.5708f, Vec3(1.0f, 0.0f, 0.0f)))
    quadrics[20].clipper2.transform(redBoxM)

    // X-Slab (Left/Right)
    quadrics[21].surface.set(Quadric.unitSlab.clone())
    quadrics[21].surface.transform(Mat4().rotate(1.5708f, Vec3(0.0f, 0.0f, 1.0f)))
    quadrics[21].surface.transform(redBoxM)
    quadrics[21].clipper.set(Quadric.unitClipper.clone())
    quadrics[21].clipper.transform(redBoxM)
    quadrics[21].clipper2.set(Quadric.unitClipper.clone())
    quadrics[21].clipper2.transform(Mat4().rotate(1.5708f, Vec3(1.0f, 0.0f, 0.0f)))
    quadrics[21].clipper2.transform(redBoxM)

    // Z-Slab (Front/Back)
    quadrics[22].surface.set(Quadric.unitSlab.clone())
    quadrics[22].surface.transform(Mat4().rotate(1.5708f, Vec3(1.0f, 0.0f, 0.0f)))
    quadrics[22].surface.transform(redBoxM)
    quadrics[22].clipper.set(Quadric.unitClipper.clone())
    quadrics[22].clipper.transform(redBoxM)
    quadrics[22].clipper2.set(Quadric.unitClipper.clone())
    quadrics[22].clipper2.transform(Mat4().rotate(1.5708f, Vec3(0.0f, 0.0f, 1.0f)))
    quadrics[22].clipper2.transform(redBoxM)


    // Blue Box (indices 23-25)
    val blueBoxM = Mat4().scale(0.9f, 0.5f, 0.9f).translate(Vec3(6.5f, -4.5f, -1.0f))

    // Y-Slab
    quadrics[23].surface.set(Quadric.unitSlab.clone())
    quadrics[23].surface.transform(blueBoxM)
    quadrics[23].clipper.set(Quadric.unitClipper.clone())
    quadrics[23].clipper.transform(Mat4().rotate(1.5708f, Vec3(0.0f, 0.0f, 1.0f)))
    quadrics[23].clipper.transform(blueBoxM)
    quadrics[23].clipper2.set(Quadric.unitClipper.clone())
    quadrics[23].clipper2.transform(Mat4().rotate(1.5708f, Vec3(1.0f, 0.0f, 0.0f)))
    quadrics[23].clipper2.transform(blueBoxM)

    // X-Slab
    quadrics[24].surface.set(Quadric.unitSlab.clone())
    quadrics[24].surface.transform(Mat4().rotate(1.5708f, Vec3(0.0f, 0.0f, 1.0f)))
    quadrics[24].surface.transform(blueBoxM)
    quadrics[24].clipper.set(Quadric.unitClipper.clone())
    quadrics[24].clipper.transform(blueBoxM)
    quadrics[24].clipper2.set(Quadric.unitClipper.clone())
    quadrics[24].clipper2.transform(Mat4().rotate(1.5708f, Vec3(1.0f, 0.0f, 0.0f)))
    quadrics[24].clipper2.transform(blueBoxM)

    // Z-Slab
    quadrics[25].surface.set(Quadric.unitSlab.clone())
    quadrics[25].surface.transform(Mat4().rotate(1.5708f, Vec3(1.0f, 0.0f, 0.0f)))
    quadrics[25].surface.transform(blueBoxM)
    quadrics[25].clipper.set(Quadric.unitClipper.clone())
    quadrics[25].clipper.transform(blueBoxM)
    quadrics[25].clipper2.set(Quadric.unitClipper.clone())
    quadrics[25].clipper2.transform(Mat4().rotate(1.5708f, Vec3(0.0f, 0.0f, 1.0f)))
    quadrics[25].clipper2.transform(blueBoxM)

    // ICICLES (Hovering above the tree)
    val icicleY = 5.5f
    
    // Icicle 1
    quadrics[26].surface.set(Quadric.paraboloid.clone())
    quadrics[26].surface.transform(Mat4().scale(0.25f, 1.5f, 0.25f).translate(Vec3(4.2f, icicleY, 0.5f)))
    quadrics[26].clipper.set(Quadric.unitClipper.clone())
    quadrics[26].clipper.transform(Mat4().scale(0.25f, 1.5f, 0.25f).translate(Vec3(4.2f, icicleY, 0.5f)))

    // Icicle 2
    quadrics[27].surface.set(Quadric.paraboloid.clone())
    quadrics[27].surface.transform(Mat4().scale(0.3f, 1.8f, 0.3f).translate(Vec3(5.0f, icicleY, -0.2f)))
    quadrics[27].clipper.set(Quadric.unitClipper.clone())
    quadrics[27].clipper.transform(Mat4().scale(0.3f, 1.8f, 0.3f).translate(Vec3(5.0f, icicleY, -0.2f)))

    // Icicle 3
    quadrics[28].surface.set(Quadric.paraboloid.clone())
    quadrics[28].surface.transform(Mat4().scale(0.2f, 1.2f, 0.2f).translate(Vec3(5.8f, icicleY, 0.2f)))
    quadrics[28].clipper.set(Quadric.unitClipper.clone())
    quadrics[28].clipper.transform(Mat4().scale(0.2f, 1.2f, 0.2f).translate(Vec3(5.8f, icicleY, 0.2f)))
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
