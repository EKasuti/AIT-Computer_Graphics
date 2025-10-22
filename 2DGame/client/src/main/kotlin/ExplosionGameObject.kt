import vision.gears.webglmath.Vec3

class ExplosionGameObject(
  mesh: Mesh,
  val duration: Float = 0.5f,
  val frameCount: Int = 16
) : GameObject(mesh) {

  private var timeAlive = 0.0f

  override fun move(
    dt: Float,
    t: Float,
    keysPressed: Set<String>,
    gameObjects: List<GameObject>
  ): Boolean {
    timeAlive += dt

    val frameIndex = ((timeAlive / duration) * frameCount).toInt().coerceAtMost(frameCount - 1)
    val frameX = (frameIndex % 4) / 4.0f
    val frameY = (frameIndex / 4) / 4.0f

    this["textureOffset"]?.set(frameX, frameY)

    return timeAlive < duration
  }
}
