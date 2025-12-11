import vision.gears.webglmath.*
import kotlin.math.*
import kotlin.random.Random

class FlameGameObject(
  mesh: Mesh,
  val parent: GameObject,
  val localOffset: Vec3
) : GameObject(mesh) {

  var isActive = false

  override fun move(
    dt: Float,
    t: Float,
    keysPressed: Set<String>,
    gameObjects: List<GameObject>
  ): Boolean {

    val cosR = cos(parent.roll)
    val sinR = sin(parent.roll)
    position.set(
      parent.position.x + localOffset.x * cosR - localOffset.y * sinR,
      parent.position.y + localOffset.x * sinR + localOffset.y * cosR,
      parent.position.z
    )

    roll = parent.roll + -PI.toFloat() / 2.0f

    if (isActive) {
      val flicker = 1.0f + ((Random.nextFloat() - 0.5f) * 0.3f)
      scale.set(1.5f * flicker, 2.0f * flicker, 1.0f)
    } else {
      scale.set(0.0f, 0.0f, 1.0f)
    }

    return true
  }

  fun draw(scene: Scene, camera: OrthoCamera) {
    if (isActive) {
      super.draw(scene, camera)
    }
  }
}
