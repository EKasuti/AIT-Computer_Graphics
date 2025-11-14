import vision.gears.webglmath.UniformProvider
import vision.gears.webglmath.Vec2
import vision.gears.webglmath.Vec3
import vision.gears.webglmath.Mat4
import kotlin.math.tan
import org.w3c.dom.events.*

class PerspectiveCamera(vararg programs : Program) : UniformProvider("camera") {

  val position by Vec3(0.0f, 0.0f, 1.0f) 
  var roll = 0.0f
  var pitch = 0.0f
  var yaw = 0.0f

  var fov = 1.0f 
  var aspect = 1.0f
  var nearPlane = 0.1f
  var farPlane = 1000.0f 

  var ahead = Vec3(0.0f, 0.0f,-1.0f) 
  var right = Vec3(1.0f, 0.0f, 0.0f) 
  var up    = Vec3(0.0f, 1.0f, 0.0f)  

  var speed = 10.5f
  var isDragging = false
  val mouseDelta = Vec2(0.0f, 0.0f) 

  val rotationMatrix = Mat4()
  val viewProjMatrix by Mat4()
  val rayDirMatrix by Mat4()

  // Car following parameters
  var followTarget: Vec3? = null
  var followDistance = 50.0f
  var followHeight = 8.0f
  var followSmoothing = 5.0f

  companion object {
    val worldUp = Vec3(0.0f, 1.0f, 0.0f)
  }

  init {
    update()
    addComponentsAndGatherUniforms(*programs)
  }
  
  fun update() { 
    rotationMatrix.set(). 
      rotate(roll).
      rotate(pitch, 1.0f, 0.0f, 0.0f).
      rotate(yaw, 0.0f, 1.0f, 0.0f)
    viewProjMatrix.set(rotationMatrix).
      translate(position).
      invert()

    val yScale = 1.0f / tan(fov * 0.5f) 
    val xScale = yScale / aspect
    val f = farPlane 
    val n = nearPlane 
    viewProjMatrix *= Mat4( 
        xScale ,    0.0f ,         0.0f ,   0.0f, 
          0.0f ,  yScale ,         0.0f ,   0.0f, 
          0.0f ,    0.0f ,  (n+f)/(n-f) ,  -1.0f, 
          0.0f ,    0.0f ,  2*n*f/(n-f) ,   0.0f)

    rayDirMatrix.set().translate(position)
    rayDirMatrix *= viewProjMatrix
    rayDirMatrix.invert()
  }

  fun setAspectRatio(ar : Float) { 
    aspect = ar
    update()
  } 

  fun move(dt : Float, keysPressed : Set<String>) { 
    // Handle camera following
    followTarget?.let { target ->
      val targetPos = Vec3(
        target.x,
        target.y + followHeight,
        target.z + followDistance
      )
      
      // Camera positions
      position.x += (targetPos.x - position.x) * dt * followSmoothing
      position.y += (targetPos.y - position.y) * dt * followSmoothing
      position.z += (targetPos.z - position.z) * dt * followSmoothing
      
      // Look at the car
      val lookDir = target - position
      lookDir.normalize()
      
      yaw = kotlin.math.atan2(lookDir.x, -lookDir.z)
      pitch = -kotlin.math.asin(lookDir.y)
    }
    
    // Manual camera control
    if(isDragging) { 
      followTarget = null // Disable following
      yaw -= mouseDelta.x * 0.002f
      pitch -= mouseDelta.y * 0.002f 
      if(pitch > 3.14f/2.0f) { 
        pitch = 3.14f/2.0f 
      } 
      if(pitch < -3.14f/2.0f) { 
        pitch = -3.14f/2.0f 
      } 
      mouseDelta.set()
    }
    
    var manualControl = false
    if("W" in keysPressed) { 
      followTarget = null // Disable following
      position += ahead * (speed * dt)
      manualControl = true
    } 
    if("S" in keysPressed) { 
      followTarget = null
      position -= ahead * (speed * dt)
      manualControl = true
    } 
    if("D" in keysPressed) { 
      followTarget = null
      position += right * (speed * dt)
      manualControl = true
    } 
    if("A" in keysPressed) { 
      followTarget = null
      position -= right * (speed * dt)
      manualControl = true
    } 
    if("E" in keysPressed) { 
      followTarget = null
      position += up * (speed * dt)
      manualControl = true
    } 
    if("Q" in keysPressed) { 
      followTarget = null
      position -= up * (speed * dt)
      manualControl = true
    } 

    update()
    ahead = (Vec3(0.0f, 0.0f, -1.0f).xyz0 * rotationMatrix).xyz
    right = (Vec3(1.0f, 0.0f,  0.0f).xyz0 * rotationMatrix).xyz
    up    = (Vec3(0.0f, 1.0f,  0.0f).xyz0 * rotationMatrix).xyz    
  }
  
  fun followCar(carPosition: Vec3) {
    followTarget = carPosition
  }
  
  fun stopFollowing() {
    followTarget = null
  }
  
  fun mouseDown() { 
    isDragging = true 
    mouseDelta.set() 
  } 

  fun mouseMove(event : MouseEvent) { 
    mouseDelta.x += event.asDynamic().movementX as Float
    mouseDelta.y += event.asDynamic().movementY as Float
    event.preventDefault()
  } 
  fun mouseUp() { 
    isDragging = false
  }  
}