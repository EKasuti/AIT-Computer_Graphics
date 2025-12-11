import vision.gears.webglmath.*
import kotlin.math.*

class BulletGameObject(
  mesh: Mesh,
  val parent: GameObject,
  direction: Vec3
) : GameObject(mesh) {

  private val velocity = direction * 20.0f // buleet speed
  private var lifetime = 2.0f // seconds

  init {
    // start position slightly ahead of the parent
    position.set(parent.position + direction * 1.5f) 
    scale.set(0.5f, 0.5f, 1.0f)
    roll = parent.roll - (PI.toFloat() / 2.0f)
  }

  override fun move(
    dt: Float,
    t: Float,
    keysPressed: Set<String>,
    gameObjects: List<GameObject>
  ): Boolean {

    position += velocity * dt // move forward

    // Check collision with seekers
    gameObjects.forEach { obj ->
      if (obj is SeekerGameObject) {
        val diff = position - obj.position
        if (diff.length() < 1.5f) {
          // Hit a seeker - bullet should be destroyed
          return false
        }
      }
    }

    // decrease lifetime
    lifetime -= dt
    return lifetime > 0.0f
  }
}
