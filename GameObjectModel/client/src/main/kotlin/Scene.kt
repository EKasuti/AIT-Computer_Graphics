import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.events.WheelEvent
import org.khronos.webgl.WebGLRenderingContext as GL //# GL# we need this for the constants declared ˙HUN˙ a constansok miatt kell
import vision.gears.webglmath.*
import kotlin.math.*

class Scene(
  val gl: WebGL2RenderingContext
) {

  val camera = OrthoCamera()
  var modelmatrix = Mat4()

  val vsIdle = Shader(gl, GL.VERTEX_SHADER, "idle-vs.glsl")
  val fsSolid = Shader(gl, GL.FRAGMENT_SHADER, "solid-fs.glsl")
  val solidProgram = Program(gl, vsIdle, fsSolid, Program.PC)
  val triangleGeometry = TriangleGeometry(gl)

  val blueMaterial = Material(solidProgram).apply {
    this["color"]?.set(Vec3(0.0f, 0.0f, 1.0f)) // blue
  }
  val greenMaterial = Material(solidProgram).apply {
    this["color"]?.set(Vec3(0.0f, 1.0f, 0.0f)) // green
  }
  val lightBlueMaterial = Material(solidProgram).apply {
    this["color"]?.set(Vec3(0.0f, 1.0f, 1.0f)) // light blue
  }
  val selectionMaterial = Material(solidProgram).apply {
    this["color"]?.set(Vec3(1.0f, 1.0f, 0.0f)) // yellow highlight
  }

  // enemy mesh (heart path)
  val enemyMesh = Mesh(blueMaterial, triangleGeometry)

  // legacy three triangles with positions
  val triangles = mutableListOf(
    Pair(Mesh(blueMaterial, triangleGeometry), Vec2(-1f, -0.6f)),
    Pair(Mesh(greenMaterial, triangleGeometry), Vec2(0.2f, 1.0f)),
    Pair(Mesh(lightBlueMaterial, triangleGeometry), Vec2(1.2f, -0.6f))
  )

  // selection + control
  val selectedTriangles = mutableSetOf<Mesh>()
  var selectedRotation = 0.0f
  var zoomLevel = 1.0f
  var lastMousePos: Vec2? = null
  var isPanning = false
  var t = 0.0f // time
  private var rotateCooldownFrames = 0

  fun resize(canvas: HTMLCanvasElement) {
    gl.viewport(0, 0, canvas.width, canvas.height)
    camera.setAspectRatio(canvas.width.toFloat() / canvas.height.toFloat())
  }

  fun update(keysPressed: Set<String>) {
    if (selectedTriangles.isEmpty()) {
      if ("W" in keysPressed) camera.position.y += 0.01f
      if ("A" in keysPressed) camera.position.x -= 0.01f
      if ("S" in keysPressed) camera.position.y -= 0.01f
      if ("D" in keysPressed) camera.position.x += 0.01f
    }

    // Move selected objects with arrow keys
    if (selectedTriangles.isNotEmpty()) {
      val step = 0.02f
      for ((mesh, pos) in triangles) {
        if (mesh in selectedTriangles) {
          if ("UP" in keysPressed) pos.y += step
          if ("DOWN" in keysPressed) pos.y -= step
          if ("LEFT" in keysPressed) pos.x -= step
          if ("RIGHT" in keysPressed) pos.x += step
        }
      }
    }

    // Rotate selected objects among grid slots
    if (rotateCooldownFrames > 0) rotateCooldownFrames--
    if (selectedTriangles.isNotEmpty() && rotateCooldownFrames == 0) {
      if ("A" in keysPressed) { // counter-clockwise: TR->TL, TL->BL, BL->TR
        rotateSelectedOnGrid(clockwise = false)
        rotateCooldownFrames = 10
      } else if ("D" in keysPressed) { // clockwise: TL->TR, TR->BL, BL->TL
        rotateSelectedOnGrid(clockwise = true)
        rotateCooldownFrames = 10
      }
    }

    if ("Q" in keysPressed) camera.roll += 0.01f
    if ("E" in keysPressed) camera.roll -= 0.01f
    if ("G" in keysPressed) arrangeOnGrid()
    if ("ESCAPE" in keysPressed) selectedTriangles.clear()

    if ("BACK_SPACE" in keysPressed || "DELETE" in keysPressed) {
      triangles.removeAll { (mesh, _) -> mesh in selectedTriangles }
      selectedTriangles.clear()
    }    

    // Zooming
    if ("Z" in keysPressed) zoomLevel *= 1.02f // zoom in
    if ("X" in keysPressed) zoomLevel *= 0.98f // zoom out
    camera.windowSize.set(2.507389f / zoomLevel, 2.0f / zoomLevel)

    // Clear + prep
    gl.clearColor(1.0f, 0.0f, 0.0f, 1.0f)
    gl.clear(GL.COLOR_BUFFER_BIT or GL.DEPTH_BUFFER_BIT)
    camera.updateViewProjMatrix()
    gl.useProgram(solidProgram.glProgram)

    // Draw all static triangles
    for ((mesh, pos) in triangles) {
      modelmatrix.set()
        .translate(pos.x, pos.y)
        .rotate(if (mesh in selectedTriangles) selectedRotation else 0.0f, 0.0f, 0.0f, 1.0f)
        .scale(0.5f, 0.5f)

      modelmatrix.commit(gl, gl.getUniformLocation(solidProgram.glProgram, "gameObject.modelMatrix")!!)
      camera.viewProjMatrix.commit(gl, gl.getUniformLocation(solidProgram.glProgram, "camera.viewProjMatrix")!!)

      if (mesh in selectedTriangles)
        mesh.using(selectionMaterial)?.draw()
      else
        mesh.draw()
    }

    // Moving enemy (heart curve, infinite)
    t += 0.02f
    val px = heartX(t) * 0.05f
    val py = heartY(t) * 0.05f
    val dx = dHeartX(t)
    val dy = dHeartY(t)
    val angle = atan2(dy, dx)

    modelmatrix.set()
      .translate(px, py)
      .rotate(angle, 0f, 0f, 1f)
      .scale(0.3f, 0.3f)

    modelmatrix.commit(gl, gl.getUniformLocation(solidProgram.glProgram, "gameObject.modelMatrix")!!)
    camera.viewProjMatrix.commit(gl, gl.getUniformLocation(solidProgram.glProgram, "camera.viewProjMatrix")!!)
    enemyMesh.draw()
  }

  // Heart curve parametric equations (with symbolic derivatives)
  fun heartX(t: Float): Float = 16f * sin(t).pow(3)
  fun heartY(t: Float): Float = 13f * cos(t) - 5f * cos(2f * t) - 2f * cos(3f * t) - cos(4f * t)
  fun dHeartX(t: Float): Float = 48f * sin(t).pow(2) * cos(t)
  fun dHeartY(t: Float): Float = -13f * sin(t) + 10f * sin(2f * t) + 6f * sin(3f * t) + 4f * sin(4f * t)

  fun pick(x: Float, y: Float, event: MouseEvent) {
    val clickPos = Vec2(x, -y)
    val threshold = 1.0f

    val closest = triangles.minByOrNull { (_, pos) -> (clickPos - pos).length() } ?: return
    val (mesh, pos) = closest
    val dist = (clickPos - pos).length()

    val shiftPressed = event.shiftKey

    if (dist < threshold) {
      if (shiftPressed) {
        if (mesh in selectedTriangles) selectedTriangles.remove(mesh)
        else selectedTriangles.add(mesh)
      } else {
        selectedTriangles.clear()
        selectedTriangles.add(mesh)
      }
    } else if (!shiftPressed) {
      selectedTriangles.clear()
    }
  }

  fun arrangeOnGrid() {
    val gridSpacing = 2f
    val positions = listOf(
      Vec2(-gridSpacing / 2,  gridSpacing / 2), // TL
      Vec2( gridSpacing / 2,  gridSpacing / 2), // TR
      Vec2(-gridSpacing / 2, -gridSpacing / 2)  // BL
    )
    for ((i, pair) in triangles.withIndex()) {
      if (i < positions.size) {
        pair.second.set(positions[i])
      }
    }
  }

  // Rotate selected meshes among grid slots (A=CCW, D=CW)
  private fun rotateSelectedOnGrid(clockwise: Boolean) {
    val gridSpacing = 2f
    val slots = arrayOf(
      Vec2(-gridSpacing / 2,  gridSpacing / 2), // 0 = TL
      Vec2( gridSpacing / 2,  gridSpacing / 2), // 1 = TR
      Vec2(-gridSpacing / 2, -gridSpacing / 2)  // 2 = BL
    )
    val nextCW  = intArrayOf(1, 2, 0) // TL->TR, TR->BL, BL->TL
    val nextCCW = intArrayOf(2, 0, 1) // TL->BL, TR->TL, BL->TR

    for ((mesh, pos) in triangles) {
      if (mesh !in selectedTriangles) continue

      // find nearest slot
      var bestIdx = 0
      var bestDist = Float.POSITIVE_INFINITY
      for (i in slots.indices) {
        val d = (pos - slots[i]).length()
        if (d < bestDist) { bestDist = d; bestIdx = i }
      }
      val newIdx = if (clockwise) nextCW[bestIdx] else nextCCW[bestIdx]
      pos.set(slots[newIdx])
    }
  }

  fun onScroll(deltaY: Double) {
    val factor = if (deltaY > 0) 1.05f else 0.95f
    camera.windowSize.y *= factor
    camera.windowSize.x *= factor
    camera.updateViewProjMatrix()
  }

  // Pan start
  fun onMouseDown(event: MouseEvent) {
    isPanning = true
    lastMousePos = Vec2(event.clientX.toFloat(), event.clientY.toFloat())
  }

  // Pan move
  fun onMouseMove(event: MouseEvent) {
    if (!isPanning) return
    val current = Vec2(event.clientX.toFloat(), event.clientY.toFloat())
    val delta = (lastMousePos ?: current) - current

    // Convert screen-space delta to world-space delta
    val scaleFactor = camera.windowSize.x / 800f
    camera.position.x += delta.x * scaleFactor
    camera.position.y -= delta.y * scaleFactor
    camera.updateViewProjMatrix()

    lastMousePos = current
  }

  // Pan end
  fun onMouseUp(event: MouseEvent) {
    isPanning = false
  }
}
