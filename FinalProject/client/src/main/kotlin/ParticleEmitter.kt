import vision.gears.webglmath.Vec3
import kotlin.random.Random

class ParticleEmitter(
    mesh: Mesh,
    val emissionRate: Float = 50f, // particles per second
    val particleLifetime: Float = 0.8f,
    val particleColor: Vec3 = Vec3(1f, 0.8f, 0.3f),
    val spreadAngle: Float = 0.5f,
    val initialSpeed: Float = 3f,
    val gravity: Vec3 = Vec3(0f, -5f, 0f)
) : GameObject(mesh) {

    private val particles = ArrayList<Particle>()
    private var emissionTimer = 0f
    var isEmitting = false
    var emissionDirection = Vec3(0f, 1f, 0f)

    // Emit a burst of particles
    fun emitBurst(count: Int, position: Vec3, direction: Vec3 = Vec3(0f, 1f, 0f)) {
        repeat(count) {
            val angle = (Random.nextFloat() - 0.5f) * spreadAngle
            val speed = initialSpeed * (0.5f + Random.nextFloat() * 0.5f)

            val randomDir = Vec3(
                direction.x + (Random.nextFloat() - 0.5f) * 0.5f,
                direction.y + (Random.nextFloat() - 0.5f) * 0.5f,
                0f
            )
            randomDir.normalize()

            particles.add(Particle(
                position = Vec3(position),
                velocity = randomDir * speed,
                lifetime = particleLifetime * (0.7f + Random.nextFloat() * 0.3f),
                maxLifetime = particleLifetime,
                color = Vec3(particleColor)
            ))
        }
    }

    // Continuous emission
    private fun emitContinuous(dt: Float) {
        if (!isEmitting) return

        emissionTimer += dt
        val particlesToEmit = (emissionTimer * emissionRate).toInt()

        if (particlesToEmit > 0) {
            repeat(particlesToEmit) {
                val randomDir = Vec3(
                    emissionDirection.x + (Random.nextFloat() - 0.5f) * spreadAngle,
                    emissionDirection.y + (Random.nextFloat() - 0.5f) * spreadAngle,
                    0f
                )
                randomDir.normalize()

                particles.add(Particle(
                    position = Vec3(position),
                    velocity = randomDir * initialSpeed * (0.8f + Random.nextFloat() * 0.4f),
                    lifetime = particleLifetime,
                    maxLifetime = particleLifetime,
                    color = Vec3(particleColor)
                ))
            }
            emissionTimer = 0f
        }
    }

    override fun move(
        dt: Float,
        t: Float,
        keysPressed: Set<String>,
        gameObjects: List<GameObject>
    ): Boolean {
        // Update continuous emission
        emitContinuous(dt)

        // Update all particles
        particles.forEach { particle ->
            particle.update(dt, gravity)
        }

        // Remove dead particles
        particles.removeAll { !it.isAlive() }

        return true
    }

    // Draw all particles
    fun drawParticles(scene: Scene, camera: OrthoCamera) {
        particles.forEach { particle ->
            // Create a temporary game object for each particle
            val particleObj = GameObject(meshes[0]).apply {
                position.set(particle.position)
                scale.set(0.15f, 0.15f, 1f)
            }
            particleObj.update()
            particleObj.draw(scene, camera)
        }
    }

    fun getParticleCount(): Int = particles.size
}
