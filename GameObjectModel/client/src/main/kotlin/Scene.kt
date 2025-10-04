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

  var posX = 0.0f
  var posY = 0.0f

  var modelmatrix = Mat4 ()
  var imatrix = Mat4 ()

  val vsIdle = Shader(gl, GL.VERTEX_SHADER, "idle-vs.glsl")
  val fsSolid = Shader(gl, GL.FRAGMENT_SHADER, "solid-fs.glsl")
  val fsStriped = Shader(gl, GL.FRAGMENT_SHADER, "striped-fs.glsl")

  val solidProgram = Program(gl, vsIdle, fsSolid, Program.PC)
  val stripedProgram = Program(gl, vsIdle, fsStriped, Program.PC)

  val quadGeometry = QuadGeometry(gl)
  val triangleGeometry = TriangleGeometry(gl)

   val stripedMaterialThick = Material (stripedProgram).apply{
    this["stripeWidth"]?.set (0.6f)
  }

   val stripedMaterialThin = Material (stripedProgram).apply{
    this["stripeWidth"]?.set (0.05f)
  }

  val thickStripedTriangleMesh = Mesh (stripedMaterialThick, triangleGeometry)
  val thinStripedTriangleMesh = Mesh (stripedMaterialThin, triangleGeometry)

  fun resize(canvas : HTMLCanvasElement) {
    gl.viewport(0, 0, canvas.width, canvas.height)//#viewport# tell the rasterizer which part of the canvas to draw to ˙HUN˙ a raszterizáló ide rajzoljon
    camera.setAspectRatio(canvas.width.toFloat() / canvas.height.toFloat())
  }

  @Suppress("UNUSED_PARAMETER")
  fun update(keysPressed : Set<String>) {
    val timeAtFirstFrame = Date().getTime()
    var timeAtLastFrame =  timeAtFirstFrame

    val timeAtThisFrame = Date().getTime()
    val dt = (timeAtThisFrame - timeAtLastFrame)
          .toFloat() / 1000.0f
    val t = (timeAtThisFrame - timeAtFirstFrame)
          .toFloat() / 1000.0f
    timeAtLastFrame = timeAtThisFrame

   if("W" in keysPressed) {
      camera.position.y +=  0.01f
    }
    if("A" in keysPressed) {
      camera.position.x -=  0.01f
    }
    if("S" in keysPressed) {
      camera.position.y -=  0.01f
    }
    if("D" in keysPressed) {
      camera.position.x +=  0.01f
    }
    if("Q" in keysPressed) {
      camera.roll +=  0.01f
    }
    if("E" in keysPressed) {
      camera.roll -=  0.01f
    }

    if("RIGHT" in keysPressed){
      avatarPos.x  +=  0.01f
    }
    if("LEFT" in keysPressed){
      avatarPos.x  -=  0.01f
    }
    if("UP" in keysPressed){
      avatarPos.y  +=  0.01f
    }
    if("DOWN" in keysPressed){
      avatarPos.y  -=  0.01f
    }

    if("F" in keysPressed) {
      avatarRotation += 0.001f
    }
    if("R" in keysPressed) {
      avatarScale *= 1.001f
    }

    gl.clearColor(1.0f, 0.0f, 0.0f, 1.0f)//## red, green, blue, alpha in [0, 1]
    gl.clearDepth(1.0f)//## will be useful in 3D ˙HUN˙ 3D-ben lesz hasznos
    gl.clear(GL.COLOR_BUFFER_BIT or GL.DEPTH_BUFFER_BIT)//#or# bitwise OR of flags

    camera.updateViewProjMatrix();

    modelmatrix.set().scale(avatarScale.x, avatarScale.y).rotate(avatarRotation, 0.0f, 0.0f, 1.0f).translate(avatarPos.x, avatarPos.y)
    imatrix.set()

    thickStripedTriangleMesh.draw()
    modelmatrix.commit(gl, gl.getUniformLocation(stripedProgram.glProgram, "gameObject.modelMatrix")!!)
    camera.viewProjMatrix.commit(gl, gl.getUniformLocation(stripedProgram.glProgram, "camera.viewProjMatrix")!!)

    thinStripedTriangleMesh.draw()
    imatrix.commit(gl, gl.getUniformLocation(stripedProgram.glProgram, "gameObject.modelMatrix")!!)
    camera.viewProjMatrix.commit(gl, gl.getUniformLocation(stripedProgram.glProgram, "camera.viewProjMatrix")!!)
  }
}
