import org.w3c.dom.HTMLCanvasElement
import org.khronos.webgl.WebGLRenderingContext as GL //# GL# we need this for the constants declared ˙HUN˙ a constansok miatt kell
import vision.gears.webglmath.*
import kotlin.js.Date

class Scene (
  val gl : WebGL2RenderingContext){

  val camera = OrthoCamera()
  var modelmatrix = Mat4 ()
  var imatrix = Mat4 ()

  val vsIdle = Shader(gl, GL.VERTEX_SHADER, "idle-vs.glsl")
  val fsSolid = Shader(gl, GL.FRAGMENT_SHADER, "solid-fs.glsl")
  val solidProgram = Program(gl, vsIdle, fsSolid, Program.PC)
  val triangleGeometry = TriangleGeometry(gl)


  val blueMaterial = Material(solidProgram).apply{
    this["color"]?.set(Vec3(0.0f, 0.0f, 1.0f)) // blue
  }
  val greenMaterial = Material(solidProgram).apply{
    this["color"]?.set(Vec3(0.0f, 1.0f, 0.0f)) // green
  }

  val lightBlueMaterial = Material(solidProgram).apply{
    this["color"]?.set(Vec3(0.0f, 1.0f, 1.0f)) // light blue
  }
  val selectionMaterial = Material(solidProgram).apply {
    this["color"]?.set(Vec3(1.0f, 1.0f, 0.0f)) // yellow highlight
  }

  val triangles = listOf(
    Pair(Mesh(blueMaterial, triangleGeometry), Vec2(-0.6f, -0.3f)),
    Pair(Mesh(greenMaterial, triangleGeometry), Vec2(0.2f, 0.4f)),
    Pair(Mesh(lightBlueMaterial, triangleGeometry), Vec2(0.8f, -0.4f))
  )

  var selectedTriangle: Mesh? = null
  var selectedPos: Vec2? = null

  fun resize(canvas : HTMLCanvasElement) {
    gl.viewport(0, 0, canvas.width, canvas.height)//#viewport# tell the rasterizer which part of the canvas to draw to ˙HUN˙ a raszterizáló ide rajzoljon
    camera.setAspectRatio(canvas.width.toFloat() / canvas.height.toFloat())
  }

  fun update(keysPressed: Set<String>) {
    if ("W" in keysPressed) camera.position.y += 0.01f
    if ("A" in keysPressed) camera.position.x -= 0.01f
    if ("S" in keysPressed) camera.position.y -= 0.01f
    if ("D" in keysPressed) camera.position.x += 0.01f
    if ("Q" in keysPressed) camera.roll += 0.01f
    if ("E" in keysPressed) camera.roll -= 0.01f
    if ("G" in keysPressed) arrangeOnGrid()

    // Move only the selected triangle
    selectedPos?.let {
      if ("UP" in keysPressed) it.y += 0.02f
      if ("DOWN" in keysPressed) it.y -= 0.02f
      if ("LEFT" in keysPressed) it.x -= 0.02f
      if ("RIGHT" in keysPressed) it.x += 0.02f
    }

    gl.clearColor(1.0f, 0.0f, 0.0f, 1.0f)//## red, green, blue, alpha in [0, 1]
    gl.clearDepth(1.0f)//## will be useful in 3D ˙HUN˙ 3D-ben lesz hasznos
    gl.clear(GL.COLOR_BUFFER_BIT or GL.DEPTH_BUFFER_BIT)//#or# bitwise OR of flags

    camera.updateViewProjMatrix()

     // draw all triangles
    gl.useProgram(solidProgram.glProgram)
    for ((mesh, pos) in triangles) {
      modelmatrix.set().translate(pos.x, pos.y).scale(0.5f, 0.5f)
      modelmatrix.commit(gl, gl.getUniformLocation(solidProgram.glProgram, "gameObject.modelMatrix")!!)
      camera.viewProjMatrix.commit(gl, gl.getUniformLocation(solidProgram.glProgram, "camera.viewProjMatrix")!!)
      mesh.draw()
    }

    // highlight selected triangle
    selectedTriangle?.let { mesh ->
      val pos = selectedPos ?: return
      gl.useProgram(solidProgram.glProgram)
      modelmatrix.set().translate(pos.x, pos.y).scale(0.5f, 0.5f)
      modelmatrix.commit(gl, gl.getUniformLocation(solidProgram.glProgram, "gameObject.modelMatrix")!!)
      camera.viewProjMatrix.commit(gl, gl.getUniformLocation(solidProgram.glProgram, "camera.viewProjMatrix")!!)
      mesh.using(selectionMaterial)?.draw()
    }
  }

  fun pick(x: Float, y: Float) {
    val clickPos = Vec2(x, -y)
    val threshold = 0.5f

    // closest triangle
    val closest = triangles.minByOrNull { (_, pos) ->
      (clickPos - pos).length()
    }!!

    val closestMesh = closest.first
    val closestPos = closest.second
    val dist = (clickPos - closestPos).length()

    if (dist < threshold) {
      selectedTriangle = closestMesh
      selectedPos = closestPos
    } else {
      selectedTriangle = null
      selectedPos = null
    }
  }

  private fun arrangeOnGrid() {
    val gridSpacingX = 2f
    val gridSpacingY = 2f

    val positions = listOf(
      Vec2(-gridSpacingX / 2,  gridSpacingY / 2), // top-left
      Vec2( gridSpacingX / 2,  gridSpacingY / 2), // top-right
      Vec2(-gridSpacingX / 2, -gridSpacingY / 2) // bottom-left
    )

    for ((i, pair) in triangles.withIndex()) {
      if (i < positions.size) {
        val pos = pair.second
        pos.x = positions[i].x
        pos.y = positions[i].y
      }
    }
  }
}
