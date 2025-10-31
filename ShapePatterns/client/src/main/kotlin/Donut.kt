import org.khronos.webgl.WebGLRenderingContext as GL
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.PI

class DonutGeometry(val gl : WebGL2RenderingContext) {

  val segments = 32 // segments for the circles
  val outerRadius = 0.4f
  val innerRadius = 0.15f
  
  val vertexBuffer = gl.createBuffer()
  init{
    gl.bindBuffer(GL.ARRAY_BUFFER, vertexBuffer) //#ARRAY_BUFFER# OpenGL dictionary:; array buffer means vertex buffer #bind# OpenGL phraseology:; Binding means: select as current. Further operations on the same target affect the bound resource.
    
    val vertices = mutableListOf<Float>()
    
    // Vertices for both outer and inner circles
    for (i in 0 until segments) {
      val angle = (i * 2.0 * PI / segments).toFloat()
      
      // Outer circle vertex
      vertices.add(outerRadius * cos(angle))
      vertices.add(outerRadius * sin(angle))
      vertices.add(0.0f)
      
      // Inner circle vertex
      vertices.add(innerRadius * cos(angle))
      vertices.add(innerRadius * sin(angle))
      vertices.add(0.0f)
    }
    
    gl.bufferData(GL.ARRAY_BUFFER,
      Float32Array(vertices.toTypedArray()),
      GL.STATIC_DRAW)
  }

  val colorBuffer = gl.createBuffer()
  init {
    gl.bindBuffer(GL.ARRAY_BUFFER, colorBuffer) //#ARRAY_BUFFER# OpenGL dictionary:; array buffer means vertex buffer #bind# OpenGL phraseology:; Binding means: select as current. Further operations on the same target affect the bound resource.

    val colors = mutableListOf<Float>()
    for (i in 0 until segments * 2) {
      colors.add(0.0f)
      colors.add(1.0f)
      colors.add(0.0f)
    }

    gl.bufferData(
      GL.ARRAY_BUFFER,
      Float32Array(colors.toTypedArray()),
      GL.STATIC_DRAW
    )
  }

  val indexBuffer = gl.createBuffer()
  init{
    gl.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, indexBuffer)
    
    val indices = mutableListOf<Short>()
    
    // Triangles between outer and inner circle vertices
    for (i in 0 until segments) {
      val outerCurrent = (i * 2).toShort()
      val innerCurrent = (i * 2 + 1).toShort()
      val outerNext = ((i + 1) % segments * 2).toShort()
      val innerNext = ((i + 1) % segments * 2 + 1).toShort()
      
      // First triangle
      indices.add(outerCurrent)
      indices.add(innerCurrent)
      indices.add(outerNext)
      
      // Second triangle
      indices.add(innerCurrent)
      indices.add(innerNext)
      indices.add(outerNext)
    }
    
    gl.bufferData(GL.ELEMENT_ARRAY_BUFFER,
      Uint16Array(indices.toTypedArray()),
      GL.STATIC_DRAW)
  }

  val inputLayout = gl.createVertexArray() //#VertexArray# OpenGL dictionary:; vertex array object (VAO) is input layout
  init{
    gl.bindVertexArray(inputLayout)

    gl.bindBuffer(GL.ARRAY_BUFFER, vertexBuffer)
    gl.enableVertexAttribArray(0)
    gl.vertexAttribPointer(0, //#0# this explains how attribute 0 can be found in the vertex buffer
      3, GL.FLOAT, //< three pieces of float
      false, //< do not normalize (make unit length)
      0, //< tightly packed
      0 //< data starts at array start
    )

    gl.bindBuffer(GL.ARRAY_BUFFER, colorBuffer)
    gl.enableVertexAttribArray(1)
    gl.vertexAttribPointer(1, //#0# this explains how attribute 0 can be found in the vertex buffer
      3, GL.FLOAT, //< three pieces of float
      false, //< do not normalize (make unit length)
      0, //< tightly packed
      0 //< data starts at array start
    )
    
    gl.bindVertexArray(null)
  }

  fun draw() {

    gl.bindVertexArray(inputLayout)
    gl.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, indexBuffer)
    
    gl.drawElements(GL.TRIANGLES, segments * 6, GL.UNSIGNED_SHORT, 0)
  }
}