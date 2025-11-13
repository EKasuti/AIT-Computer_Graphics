import vision.gears.webglmath.*
import kotlin.math.*

class Wheel(
  meshes: Array<Mesh>,
  val localOffset: Vec3,
  val isSteerable: Boolean
) : GameObject(*meshes) {

  var spinSpeed = 0f

  fun updateWheelTransform(
    carPos: Vec3,
    carYaw: Float,
    steerAngle: Float,
    rollSpeed: Float,
    dt: Float
  ) {
    // worldOffset = rotated localOffset
    val worldOffset = localOffset.clone().apply {
      val s = sin(carYaw)
      val c = cos(carYaw)

      val xOld = x
      val zOld = z

      x = xOld * c + zOld * s
      z = -xOld * s + zOld * c
    }

    // position
    position.set(carPos + worldOffset)

    // steering (yaw)
    yaw = carYaw
    if (isSteerable) {
      yaw += steerAngle
    }
    // rolling (pitch)
    pitch += rollSpeed * dt
  }
}
