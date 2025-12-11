import vision.gears.webglmath.*
import kotlin.math.*

class DiamondGameObject(
  mesh: Mesh,
  private val avatar: GameObject
) : GameObject(mesh) {

  private val velocity = Vec3(0f, -2f, 0f) // falling speed
  var collected = false
  private val fallRange = 20f // distance below avatar to despawn

  override fun move(
    dt: Float,
    t: Float,
    keysPressed: Set<String>,
    gameObjects: List<GameObject>
  ): Boolean {

    //constant falling velocity
    position += velocity * dt

    // collision
    val toAvatar = avatar.position - position
    if (toAvatar.length() < 1.2f && !collected) {
      collected = true
      return false
    }

    // despawn
    if (position.y < avatar.position.y - fallRange) {
      return false
    }

    return true
  }
}
