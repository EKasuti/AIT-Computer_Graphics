import org.w3c.dom.HTMLCanvasElement
import org.khronos.webgl.WebGLRenderingContext as GL //# GL# we need this for the constants declared ˙HUN˙ a constansok miatt kell
import vision.gears.webglmath.*
import kotlin.js.Date

class Scene (
  val gl : WebGL2RenderingContext){

  val camera = OrthoCamera()

  val avatarPos = Vec2(0.0f, 0.0f)
  var avatarRotation = 0.0f
  val avatarScale = Vec2(1.0f, 1.0f)

  var modelmatrix = Mat4 ()
  var imatrix = Mat4 ()

  val vsIdle = Shader(gl, GL.VERTEX_SHADER, "idle-vs.glsl")
  val fsSolid = Shader(gl, GL.FRAGMENT_SHADER, "solid-fs.glsl")

  val solidProgram = Program(gl, vsIdle, fsSolid, Program.PC)
  val triangleGeometry = TriangleGeometry(gl)


  val blueMaterial = Material(solidProgram).apply{
    this["color"]?.set(Vec3(0.0f, 0.0f, 1.0f)) // blue
  }

  val selectionMaterial = Material(solidProgram).apply {
    this["color"]?.set(Vec3(1.0f, 1.0f, 0.0f)) // yellow highlight
  }

  val triangle = Mesh(blueMaterial, triangleGeometry)

  var isSelected = false
  val trianglePos = Vec2(0.0f, 0.0f)

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
    if ("H" in keysPressed) isSelected = !isSelected

    gl.clearColor(1.0f, 0.0f, 0.0f, 1.0f)//## red, green, blue, alpha in [0, 1]
    gl.clearDepth(1.0f)//## will be useful in 3D ˙HUN˙ 3D-ben lesz hasznos
    gl.clear(GL.COLOR_BUFFER_BIT or GL.DEPTH_BUFFER_BIT)//#or# bitwise OR of flags

    camera.updateViewProjMatrix()

    // Triangle
    gl.useProgram(solidProgram.glProgram)
    modelmatrix.set().translate(trianglePos.x, trianglePos.y)
    modelmatrix.commit(gl, gl.getUniformLocation(solidProgram.glProgram, "gameObject.modelMatrix")!!)
    camera.viewProjMatrix.commit(gl, gl.getUniformLocation(solidProgram.glProgram, "camera.viewProjMatrix")!!)
    triangle.draw()

    // On selection
    if (isSelected) {
      gl.useProgram(solidProgram.glProgram)
      modelmatrix.set().translate(trianglePos.x, trianglePos.y)
      modelmatrix.commit(gl, gl.getUniformLocation(solidProgram.glProgram, "gameObject.modelMatrix")!!)
      camera.viewProjMatrix.commit(gl, gl.getUniformLocation(solidProgram.glProgram, "camera.viewProjMatrix")!!)
      triangle.using(selectionMaterial)?.draw()
    }
  }

  // function to pick triangle
  fun pick(x: Float, y: Float) {
    val clickPos = Vec2(x, -y)
    val threshold = 0.3f
    val dist = (clickPos - trianglePos).length()

    if (dist < threshold) {
      isSelected = true
    } else {
      isSelected = false
    }
  }
}
