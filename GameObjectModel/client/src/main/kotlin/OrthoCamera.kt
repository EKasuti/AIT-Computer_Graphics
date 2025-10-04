import vision.gears.webglmath.*

class OrthoCamera() : UniformProvider("camera") {
  val position = Vec2(0.0f, 0.0f) 
  var roll = 0.0f 
  val windowSize = Vec2(2.0f, 2.0f) 
  
  val viewProjMatrix by Mat4()
  init{
    updateViewProjMatrix()
  }

  fun updateViewProjMatrix() { 
    viewProjMatrix.set(). 
      scale(0.5f, 0.5f). 
      scale(windowSize). 
      rotate(roll). 
      translate(position). 
      invert()
  }
  fun setAspectRatio(ar : Float) { 
    windowSize.x = windowSize.y * ar
    updateViewProjMatrix()
  }

}
