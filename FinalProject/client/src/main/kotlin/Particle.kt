import vision.gears.webglmath.Vec3

class Particle(
    val position: Vec3 = Vec3(),
    val velocity: Vec3 = Vec3(),
    var lifetime: Float = 1.0f,
    val maxLifetime: Float = 1.0f,
    val color: Vec3 = Vec3(1f, 1f, 1f)
) {
    fun isAlive(): Boolean = lifetime > 0f

    fun update(dt: Float, gravity: Vec3 = Vec3(0f, -5f, 0f)) {
        // Apply physics
        velocity += gravity * dt
        position += velocity * dt
        lifetime -= dt
    }

    fun getAlpha(): Float {
        // Fade out as lifetime decreases
        return (lifetime / maxLifetime).coerceIn(0f, 1f)
    }
}
