import org.khronos.webgl.WebGLRenderingContext as GL
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array
import kotlin.math.sin
import kotlin.math.cos
import kotlin.math.PI
import kotlin.math.pow

class HeartGeometry(val gl: WebGL2RenderingContext) {

  val segments = 150
  val scale = 0.01f

  val vertexBuffer = gl.createBuffer()
  init {
    gl.bindBuffer(GL.ARRAY_BUFFER, vertexBuffer) //#ARRAY_BUFFER# OpenGL dictionary:; array buffer means vertex buffer #bind#

    val vertexCoords = ArrayList<Float>()

    // Center vertex
    vertexCoords.add(0.0f)
    vertexCoords.add(0.0f)
    vertexCoords.add(0.5f)

    // Perimeter vertices
    for (i in 0..segments) {
      val t = (i.toFloat() / segments) * 2f * PI.toFloat()
      val x = (16f * sin(t).pow(3)) * scale
      val y = (13f * cos(t) - 5f * cos(2f*t) - 2f * cos(3f*t) - cos(4f*t)) * scale

      vertexCoords.add(x)
      vertexCoords.add(y)
      vertexCoords.add(0.5f)
    }

    gl.bufferData(GL.ARRAY_BUFFER,
      Float32Array(vertexCoords.toTypedArray()),
      GL.STATIC_DRAW)
  }

  val colorBuffer = gl.createBuffer()
  init {
    gl.bindBuffer(GL.ARRAY_BUFFER, colorBuffer)

    val colors = ArrayList<Float>()

    // Center color (white)
    colors.add(1.0f); colors.add(1.0f); colors.add(1.0f)

    // Perimeter colors (white)
    for (i in 0..segments) {
      colors.add(1.0f)
      colors.add(1.0f)
      colors.add(1.0f)
    }

    gl.bufferData(
      GL.ARRAY_BUFFER,
      Float32Array(colors.toTypedArray()),
      GL.STATIC_DRAW
    )
  }

  val indexBuffer = gl.createBuffer()
  init {
    gl.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, indexBuffer) //#ELEMENT_ARRAY_BUFFER# OpenGL dictionary:; element array buffer: index buffer

    val indices = ArrayList<Short>()
    for (i in 1..segments) {
      indices.add(0) // center
      indices.add(i.toShort()) // current perimeter
      indices.add((i+1).toShort()) // next perimeter
    }

    gl.bufferData(GL.ELEMENT_ARRAY_BUFFER,
      Uint16Array(indices.toTypedArray()),
      GL.STATIC_DRAW)
  }

  val inputLayout = gl.createVertexArray() //#VertexArray# OpenGL dictionary:; vertex array object (VAO) is input layout
  init {
    gl.bindVertexArray(inputLayout)

    gl.bindBuffer(GL.ARRAY_BUFFER, vertexBuffer)
    gl.enableVertexAttribArray(0)
    gl.vertexAttribPointer(0,
      3, GL.FLOAT,
      false,
      0,
      0
    )

    gl.bindBuffer(GL.ARRAY_BUFFER, colorBuffer)
    gl.enableVertexAttribArray(1)
    gl.vertexAttribPointer(1,
      3, GL.FLOAT,
      false,
      0,
      0
    )

    gl.bindVertexArray(null)
  }

  fun draw() {
    gl.bindVertexArray(inputLayout)
    gl.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, indexBuffer)

    gl.drawElements(GL.TRIANGLES, segments * 3, GL.UNSIGNED_SHORT, 0) //#segments*3# triangle fan
  }
}
