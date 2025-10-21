import vision.gears.webglmath.*

class AsteroidGameObject(mesh : Mesh) 
: GameObject(mesh){
    val velocity = Vec3()
    override fun move(
      dt : Float,
      t : Float,
      keysPressed : Set<String>,
      gameObjects : List<GameObject>
      ) : Boolean {
      roll += dt
      position += velocity * dt
//      position.set(cos(t), sin(t))
      return true
    }

}
