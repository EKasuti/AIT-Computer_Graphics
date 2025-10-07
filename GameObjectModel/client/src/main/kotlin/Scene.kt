import org.w3c.dom.HTMLCanvasElement
import org.khronos.webgl.WebGLRenderingContext as GL //# GL# we need this for the constants declared ˙HUN˙ a constansok miatt kell
import vision.gears.webglmath.*

class Scene (
  val gl : WebGL2RenderingContext){

  val camera = OrthoCamera()
  var modelmatrix = Mat4 ()

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

  val triangles = mutableListOf(
    Pair(Mesh(blueMaterial, triangleGeometry), Vec2(-0.6f, -0.3f)),
    Pair(Mesh(greenMaterial, triangleGeometry), Vec2(0.2f, 0.4f)),
    Pair(Mesh(lightBlueMaterial, triangleGeometry), Vec2(0.8f, -0.4f))
  )

  var selectedTriangle: Mesh? = null
  var selectedPos: Vec2? = null
  var selectedRotation = 0.0f

  fun resize(canvas : HTMLCanvasElement) {
    gl.viewport(0, 0, canvas.width, canvas.height)//#viewport# tell the rasterizer which part of the canvas to draw to ˙HUN˙ a raszterizáló ide rajzoljon
    camera.setAspectRatio(canvas.width.toFloat() / canvas.height.toFloat())
  }

  fun update(keysPressed: Set<String>) {
    if (selectedTriangle == null) {
      if ("W" in keysPressed) camera.position.y += 0.01f
      if ("A" in keysPressed) camera.position.x -= 0.01f
      if ("S" in keysPressed) camera.position.y -= 0.01f
      if ("D" in keysPressed) camera.position.x += 0.01f
    }

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

    if (selectedTriangle != null) {
      if ("A" in keysPressed) selectedRotation += 0.05f
      if ("D" in keysPressed) selectedRotation -= 0.05f
    }

    if ("BACK_SPACE" in keysPressed && selectedTriangle != null) {
      val index = triangles.indexOfFirst { it.first == selectedTriangle }
      if (index != -1) {
        triangles.removeAt(index)
      }
      selectedTriangle = null
      selectedPos = null
      selectedRotation = 0.0f
    }

    gl.clearColor(1.0f, 0.0f, 0.0f, 1.0f)//## red, green, blue, alpha in [0, 1]
  

    gl.clear(GL.COLOR_BUFFER_BIT or GL.DEPTH_BUFFER_BIT)//#or# bitwise OR of flags

    camera.updateViewProjMatrix()

     // draw all triangles
    gl.useProgram(solidProgram.glProgram)
    for ((mesh, pos) in triangles) {
      modelmatrix.set()
        .translate(pos.x, pos.y)
        .rotate(if (mesh == selectedTriangle) selectedRotation else 0.0f, 0.0f, 0.0f, 1.0f)
        .scale(0.5f, 0.5f)

      modelmatrix.commit(gl, gl.getUniformLocation(solidProgram.glProgram, "gameObject.modelMatrix")!!)
      camera.viewProjMatrix.commit(gl, gl.getUniformLocation(solidProgram.glProgram, "camera.viewProjMatrix")!!)

      if (mesh == selectedTriangle)
        mesh.using(selectionMaterial)?.draw()
      else
        mesh.draw()
    }
  }

  fun pick(x: Float, y: Float) {
    val clickPos = Vec2(x, -y)
    val threshold = 1.0f

    val closest = triangles.minByOrNull { (_, pos) -> (clickPos - pos).length() } ?: return
    val (mesh, pos) = closest
    val dist = (clickPos - pos).length()

    if (dist < threshold) {
      selectedTriangle = mesh
      selectedPos = pos
      selectedRotation = 0.0f
    } else {
      selectedTriangle = null
      selectedPos = null
      selectedRotation = 0.0f
    }
  }

  fun arrangeOnGrid() {
    val gridSpacing = 2f
    val positions = listOf(
      Vec2(-gridSpacing / 2, gridSpacing / 2), // top-left
      Vec2(gridSpacing / 2, gridSpacing / 2), // top-right
      Vec2(-gridSpacing / 2, -gridSpacing / 2) // bottom-left
    )
    for ((i, pair) in triangles.withIndex()) {
      if (i < positions.size) {
        val pos = pair.second
        pos.set(positions[i])
      }
    }
  }
}
