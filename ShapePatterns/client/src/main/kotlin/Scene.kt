import org.w3c.dom.HTMLCanvasElement
import org.khronos.webgl.WebGLRenderingContext as GL //# GL# we need this for the constants declared ˙HUN˙ a constansok miatt kell
import kotlinx.browser.window
import kotlin.random.Random  

class Scene (
  val gl : WebGL2RenderingContext){

  var posX = -0.5f
  var posY = -0.5f

  var posX1 = -0.3f
  var posY1 = 0.5f

  val vsIdle = Shader(gl, GL.VERTEX_SHADER, "idle-vs.glsl")
  val fsSolid = Shader(gl, GL.FRAGMENT_SHADER, "solid-fs.glsl")
  val fsStriped = Shader(gl, GL.FRAGMENT_SHADER, "striped-fs.glsl")
  val fsBullseye = Shader(gl, GL.FRAGMENT_SHADER, "bullseye-fs.glsl")
  val fsCheckered = Shader(gl, GL.FRAGMENT_SHADER, "checkered-fs.glsl")

  val solidProgram = Program(gl, vsIdle, fsSolid, Program.PC)
  val stripedProgram = Program(gl, vsIdle, fsStriped, Program.PC)
  val bullseyeProgram = Program(gl, vsIdle, fsBullseye, Program.PC)
  val checkeredProgram = Program(gl, vsIdle, fsCheckered, Program.PC)

  // val quadGeometry = QuadGeometry(gl)
  // val triangleGeometry = TriangleGeometry(gl)
  val donutGeometry = DonutGeometry(gl) // donut geometry
  val crescentGeometry = CrescentGeometry(gl) // crescent geometry
  val serpentineGeometry = SerpentineGeometry(gl) // serpentine geometry
  val heartGeometry = HeartGeometry(gl) // heart geometry

  init {
    // random pos every sec
    window.setInterval({
      posX1 = Random.nextFloat() * 2f - 1f
      posY1 = Random.nextFloat() * 2f - 1f
    }, 1000) 
  }

  fun resize(canvas : HTMLCanvasElement) {
    gl.viewport(0, 0, canvas.width, canvas.height)//#viewport# tell the rasterizer which part of the canvas to draw to ˙HUN˙ a raszterizáló ide rajzoljon
  }


  @Suppress("UNUSED_PARAMETER")
  fun update(keysPressed : Set<String>) {
    //gl.enable(gl.cullFace)
    //gl.cullFace(GL_FRONT)

    // movements
    if ("D" in keysPressed) posX += 0.01f
    if ("A" in keysPressed) posX -= 0.01f
    if ("W" in keysPressed) posY += 0.01f
    if ("S" in keysPressed) posY -= 0.01f

    // screen wrapping
    if (posX > 1f) posX = -1f
    if (posX < -1f) posX = 1f
    if (posY > 1f) posY = -1f
    if (posY < -1f) posY = 1f

    

    gl.clearColor(1.0f, 0.0f, 0.0f, 1.0f)//## red, green, blue, alpha in [0, 1]
    gl.clearDepth(1.0f)//## will be useful in 3D ˙HUN˙ 3D-ben lesz hasznos
    gl.clear(GL.COLOR_BUFFER_BIT or GL.DEPTH_BUFFER_BIT)//#or# bitwise OR of flags

    // gl.useProgram(solidProgram.glProgram)

    // ASSIGNMENT
    // DONUT
    gl.useProgram(stripedProgram.glProgram)

    gl.uniform3f(
      gl.getUniformLocation(
        stripedProgram.glProgram,
        "gameObject.position"),
      posX, posY, 0.0f)

    // blue stripes
    gl.uniform3f(
      gl.getUniformLocation(
        stripedProgram.glProgram,
        "stripe.primaryColor"),
      0.0f, 0.0f, 1.0f
    )

    // white stripes
    gl.uniform3f(
      gl.getUniformLocation(
        stripedProgram.glProgram,
        "stripe.secondaryColor"),
      1.0f, 1.0f, 1.0f
    )

    // stripe width
    gl.uniform1f(
      gl.getUniformLocation(
        stripedProgram.glProgram,
        "stripe.stripeWidth"),
      0.2f
    )

    donutGeometry.draw()

    // CRESCENT
    gl.useProgram(solidProgram.glProgram)
    
    gl.uniform3f(
      gl.getUniformLocation(
        solidProgram.glProgram,
        "gameObject.position"),
      0.7f, 0.7f, 0.5f)

    gl.uniform3f(
      gl.getUniformLocation(
        solidProgram.glProgram,
        "material.color"),
      1.0f, 1.0f, 0.0f)

    crescentGeometry.draw()

    // SERPENTINE STRIP
    gl.useProgram(checkeredProgram.glProgram)

    gl.uniform3f(
      gl.getUniformLocation(checkeredProgram.glProgram,
      "gameObject.position"),
      0.0f, 0.2f, 0.0f
    )

    gl.uniform3f(
      gl.getUniformLocation(checkeredProgram.glProgram, "check.colorA"),
      1.0f, 1.0f, 1.0f
    )

    gl.uniform3f(
      gl.getUniformLocation(checkeredProgram.glProgram, "check.colorB"),
      0.0f, 0.0f, 0.0f
    )

    gl.uniform1f(
      gl.getUniformLocation(checkeredProgram.glProgram, "check.squareSize"),
      0.12f
    )
   
    serpentineGeometry.draw()

    // HEART
    gl.useProgram(bullseyeProgram.glProgram)

    gl.uniform3f(
      gl.getUniformLocation(
        bullseyeProgram.glProgram,
        "gameObject.position"),
      posX1, posY1, 0.0f
    )
    gl.uniform3f(
      gl.getUniformLocation(
        bullseyeProgram.glProgram,
        "bullseye.primaryColor"),
      1.0f, 0.0f, 1.0f
    )

    gl.uniform3f(
      gl.getUniformLocation(
        bullseyeProgram.glProgram,
        "bullseye.secondaryColor"),
      0.0f, 0.0f, 0.0f
    )

    gl.uniform1f(
      gl.getUniformLocation(
        bullseyeProgram.glProgram,
        "bullseye.bandWidth"),
      0.02f
    )
    heartGeometry.draw()
  }
}
