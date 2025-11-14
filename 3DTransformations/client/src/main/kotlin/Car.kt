import vision.gears.webglmath.*
import kotlin.math.*

class Car(
  chassisMeshes: Array<Mesh>,
  val wheels: List<Wheel>
) : GameObject(*chassisMeshes) {

  var speed = 0f
  var heading = 0f
  var steeringAngle = 0f

  var dt = 0f

  fun control(dt: Float, keysPressed: Set<String>) {
    this.dt = dt

    if ("I" in keysPressed) speed += 40f * dt
    if ("K" in keysPressed) speed -= 40f * dt

    speed *= 0.98f

    when {
      "J" in keysPressed  -> steeringAngle = +0.1f
      "L" in keysPressed -> steeringAngle = -0.1f
      else -> steeringAngle *= 0.5f
    }

    heading += steeringAngle * speed * 0.4f * dt
    yaw = heading

    position.x += sin(yaw) * speed * dt
    position.z += cos(yaw) * speed * dt
  }

  fun updateHierarchy() {
    val rollSpeed = speed * 3f

    wheels.forEach { wheel ->
      wheel.updateWheelTransform(
        carPos = position,
        carYaw = yaw,
        steerAngle = steeringAngle,
        rollSpeed = rollSpeed,
        dt = dt
      )
    }
  }

  fun getObjects(): List<GameObject> =
    listOf(this) + wheels
}
