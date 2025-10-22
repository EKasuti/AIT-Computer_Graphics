import vision.gears.webglmath.Vec3

class PlatformGameObject(
  mesh: Mesh,
  val yLevel: Float = -5.0f
) : GameObject(mesh) {

  init {
    position.set(0.0f, yLevel, 0.0f)
    scale.set(6.0f, 1.0f, 1.0f)
  }
}
