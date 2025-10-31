import org.khronos.webgl.WebGLRenderingContext as GL
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array
import kotlin.math.sin
import kotlin.math.PI

class SerpentineGeometry(val gl: WebGL2RenderingContext) {

  val segments = 100
  val amplitude = 0.2f
  val wavelength = 2f * PI.toFloat() / 5f
  val halfHeight = 0.1f

  val vertexBuffer = gl.createBuffer()
  init {
    gl.bindBuffer(GL.ARRAY_BUFFER, vertexBuffer) //#ARRAY_BUFFER# vertex positions

    val vertexCoords = ArrayList<Float>()

    for (i in 0..segments) {
      val x = (i.toFloat() / segments) * 2f - 1f
      val yCenter = amplitude * sin(x / wavelength * 2f * PI.toFloat())

      // top vertex
      vertexCoords.add(x)
      vertexCoords.add(yCenter + halfHeight)
      vertexCoords.add(0f)

      // bottom vertex
      vertexCoords.add(x)
      vertexCoords.add(yCenter - halfHeight)
      vertexCoords.add(0f)
    }

    gl.bufferData(GL.ARRAY_BUFFER,
      Float32Array(vertexCoords.toTypedArray()),
      GL.STATIC_DRAW)
  }

  val colorBuffer = gl.createBuffer()
  init {
    gl.bindBuffer(GL.ARRAY_BUFFER, colorBuffer) //#ARRAY_BUFFER# vertex colors

    val colors = ArrayList<Float>()

    for (i in 0..segments) {
      // top vertex color (cyan)
      colors.add(0.0f); colors.add(1.0f); colors.add(1.0f)
      // bottom vertex color (blue)
      colors.add(0.0f); colors.add(0.0f); colors.add(1.0f)
    }

    gl.bufferData(GL.ARRAY_BUFFER,
      Float32Array(colors.toTypedArray()),
      GL.STATIC_DRAW)
  }

  val indexBuffer = gl.createBuffer()
  init {
    gl.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, indexBuffer) //#ELEMENT_ARRAY_BUFFER# index buffer

    val indices = ArrayList<Short>()

    for (i in 0 until segments) {
      val topCurr = (i * 2).toShort()
      val botCurr = (i * 2 + 1).toShort()
      val topNext = ((i + 1) * 2).toShort()
      val botNext = ((i + 1) * 2 + 1).toShort()

      // first triangle
      indices.add(topCurr)
      indices.add(botCurr)
      indices.add(topNext)

      // second triangle
      indices.add(botCurr)
      indices.add(botNext)
      indices.add(topNext)
    }

    gl.bufferData(GL.ELEMENT_ARRAY_BUFFER,
      Uint16Array(indices.toTypedArray()),
      GL.STATIC_DRAW)
  }

  val inputLayout = gl.createVertexArray() //#VertexArray# VAO input layout
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
    gl.drawElements(GL.TRIANGLES, segments * 6, GL.UNSIGNED_SHORT, 0)
  }
}
