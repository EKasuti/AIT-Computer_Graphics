import org.khronos.webgl.WebGLRenderingContext as GL
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.PI

class CrescentGeometry(val gl: WebGL2RenderingContext) {

  val segments = 32
  val outerRadius = 0.2f
  val innerRadius = 0.15f
  val offsetX = -0.1f

  // Outer geometry - matches the background color
  val outerVertexBuffer = gl.createBuffer()
  val outerColorBuffer = gl.createBuffer()
  val outerIndexBuffer = gl.createBuffer()
  val outerInputLayout = gl.createVertexArray()

  // Inner geometry - yellow (cause why not)
  val innerVertexBuffer = gl.createBuffer()
  val innerColorBuffer = gl.createBuffer()
  val innerIndexBuffer = gl.createBuffer()
  val innerInputLayout = gl.createVertexArray()

  init {
    createOuterGeometry()
    createInnerGeometry()
  }

  private fun createOuterGeometry() {

    gl.bindBuffer(GL.ARRAY_BUFFER, outerVertexBuffer)
    
    val outerVertices = mutableListOf<Float>()
    
    // Center vertex
    outerVertices.add(0.0f) // x
    outerVertices.add(0.0f) // y
    outerVertices.add(0.0f) // z
    
    // Circle vertices
    for (i in 0..segments) {
      val angle = (i.toFloat() / segments) * (2f * PI.toFloat())
      val x = outerRadius * cos(angle)
      val y = outerRadius * sin(angle)
      outerVertices.add(x)
      outerVertices.add(y)
      outerVertices.add(0.0f)
    }
    
    gl.bufferData(GL.ARRAY_BUFFER, Float32Array(outerVertices.toTypedArray()), GL.STATIC_DRAW)

    // Color to match the background. Using red for now 
    gl.bindBuffer(GL.ARRAY_BUFFER, outerColorBuffer)
    
    val outerColors = mutableListOf<Float>()
    for (i in 0..(segments + 1)) {
      outerColors.add(1.0f) // r
      outerColors.add(1.0f) // g
      outerColors.add(0.0f) // b
    }
    
    gl.bufferData(GL.ARRAY_BUFFER, Float32Array(outerColors.toTypedArray()), GL.STATIC_DRAW)

    gl.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, outerIndexBuffer)
    
    val outerIndices = mutableListOf<Short>()
    for (i in 0 until segments) {
      outerIndices.add(0) // center
      outerIndices.add((i + 1).toShort())
      outerIndices.add((i + 2).toShort())
    }
    
    gl.bufferData(GL.ELEMENT_ARRAY_BUFFER, Uint16Array(outerIndices.toTypedArray()), GL.STATIC_DRAW)

    gl.bindVertexArray(outerInputLayout)

    gl.bindBuffer(GL.ARRAY_BUFFER, outerVertexBuffer)
    gl.enableVertexAttribArray(0)
    gl.vertexAttribPointer(0, 3, GL.FLOAT, false, 0, 0)

    gl.bindBuffer(GL.ARRAY_BUFFER, outerColorBuffer)
    gl.enableVertexAttribArray(1)
    gl.vertexAttribPointer(1, 3, GL.FLOAT, false, 0, 0)

    gl.bindVertexArray(null)
  }

  private fun createInnerGeometry() {

    gl.bindBuffer(GL.ARRAY_BUFFER, innerVertexBuffer)
    
    val innerVertices = mutableListOf<Float>()
    
    // Center vertex
    innerVertices.add(offsetX) // x
    innerVertices.add(0.0f)    // y
    innerVertices.add(0.0f)    // z
    
    // Circle vertices
    for (i in 0..segments) {
      val angle = (i.toFloat() / segments) * (2f * PI.toFloat())
      val x = offsetX + innerRadius * cos(angle)
      val y = innerRadius * sin(angle)
      innerVertices.add(x)
      innerVertices.add(y)
      innerVertices.add(0.0f)
    }
    
    gl.bufferData(GL.ARRAY_BUFFER, Float32Array(innerVertices.toTypedArray()), GL.STATIC_DRAW)

    // Colors for inner disc (yellow - cresent moon)
    gl.bindBuffer(GL.ARRAY_BUFFER, innerColorBuffer)
    
    val innerColors = mutableListOf<Float>()
    for (i in 0..(segments + 1)) {
      innerColors.add(1.0f) // r
      innerColors.add(0.0f) // g
      innerColors.add(0.0f) // b
    }
    
    gl.bufferData(GL.ARRAY_BUFFER, Float32Array(innerColors.toTypedArray()), GL.STATIC_DRAW)

    gl.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, innerIndexBuffer)
    
    val innerIndices = mutableListOf<Short>()
    for (i in 0 until segments) {
      innerIndices.add(0) // center
      innerIndices.add((i + 1).toShort())
      innerIndices.add((i + 2).toShort())
    }
    
    gl.bufferData(GL.ELEMENT_ARRAY_BUFFER, Uint16Array(innerIndices.toTypedArray()), GL.STATIC_DRAW)

    gl.bindVertexArray(innerInputLayout)

    gl.bindBuffer(GL.ARRAY_BUFFER, innerVertexBuffer)
    gl.enableVertexAttribArray(0)
    gl.vertexAttribPointer(0, 3, GL.FLOAT, false, 0, 0)

    gl.bindBuffer(GL.ARRAY_BUFFER, innerColorBuffer)
    gl.enableVertexAttribArray(1)
    gl.vertexAttribPointer(1, 3, GL.FLOAT, false, 0, 0)

    gl.bindVertexArray(null)
  }

  fun draw() {
    // Outer part (red disc - background color)
    gl.bindVertexArray(outerInputLayout)
    gl.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, outerIndexBuffer)
    gl.drawElements(GL.TRIANGLES, segments * 3, GL.UNSIGNED_SHORT, 0)
    
    // Inner section (yellow disc)
    gl.bindVertexArray(innerInputLayout)
    gl.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, innerIndexBuffer)
    gl.drawElements(GL.TRIANGLES, segments * 3, GL.UNSIGNED_SHORT, 0)
  }
}