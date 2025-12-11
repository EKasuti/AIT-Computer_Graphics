import vision.gears.webglmath.*
import kotlin.math.*

class SeekerGameObject(
  mesh: Mesh,
  val target: GameObject,
  val onCollision: () -> Unit = {}  // Callback when hitting avatar
) : GameObject(mesh) {

  private val velocity = Vec3()
  private val accelerationStrength = 5.0f
  private val maxSpeed = 10.0f
  private var lifetime = 10.0f // seconds before despawn

  override fun move(
    dt: Float,
    t: Float,
    keysPressed: Set<String>,
    gameObjects: List<GameObject>
  ): Boolean {

    // vec - from seeker to target
    val toTarget = target.position - position
    val distance = toTarget.length()

    if (distance > 0.01f) {
      val direction = Vec3(toTarget)
      direction.normalize()
      velocity += direction * accelerationStrength * dt
    }

    //limit speed
    if (velocity.length() > maxSpeed) {
      velocity.normalize()
      velocity *= maxSpeed
    }

    // movement update
    position += velocity * dt

    // rotation update
    if (velocity.length() > 0.001f) {
      roll = atan2(velocity.y, velocity.x)
    }

    // lifetime check
    lifetime -= dt
    if (lifetime <= 0f) {
      return false
    }

    // collision with avatar
    val diff = target.position - position
    if (diff.length() < 1.0f) {
      onCollision()  // Trigger damage callback
      return false
    }

    return true
  }
}
