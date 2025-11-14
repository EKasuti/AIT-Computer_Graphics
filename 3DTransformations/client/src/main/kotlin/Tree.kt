import vision.gears.webglmath.*
import kotlin.random.Random

class Tree(val mesh: Mesh) : GameObject(mesh) {
  companion object {
    const val SPAWN_DISTANCE_AHEAD = 200f
    const val SPAWN_DISTANCE_BEHIND = 100f
    const val SIDE_RANGE = 80f
  }
}

class TreeManager(
  val gl: WebGL2RenderingContext,
  val material: Material
) {
  val treeMesh = Mesh(material, TexturedQuadGeometry(gl))
  val trees = ArrayList<Tree>()
  var farthestSpawnedZ = 0f
  var nearestSpawnedZ = 0f
  val spawnInterval = 25f
  val treesPerSpawn = 2
    
  fun update(carPosition: Vec3, gameObjects: ArrayList<GameObject>) {
    // Spawn new trees ahead of the car
    while (farthestSpawnedZ < carPosition.z + Tree.SPAWN_DISTANCE_AHEAD) {
      for (i in 0 until treesPerSpawn) {
        spawnTree(farthestSpawnedZ + spawnInterval, gameObjects)
      }
      farthestSpawnedZ += spawnInterval
    }
      
    // Remove trees that are too far behind the car
    while (nearestSpawnedZ > carPosition.z - Tree.SPAWN_DISTANCE_BEHIND) {
      for (i in 0 until treesPerSpawn) {
        spawnTree(nearestSpawnedZ - spawnInterval, gameObjects)
      }
      nearestSpawnedZ -= spawnInterval
    }
  }
    
  private fun spawnTree(z: Float, gameObjects: ArrayList<GameObject>) {
    val scaleValue = 0.8f + Random.nextFloat() * 25f
    
    // randomly choose left or right side
    val side = if (Random.nextFloat() > 0.5f) 1f else -1f
    val xOffset = side * (30f + Random.nextFloat() * 50f) // Wider spread
    
   // random y offset for variation
    val yOffset = Random.nextFloat() * 2f - 1f
    
    val tree = Tree(treeMesh).apply {
      position.set(xOffset, yOffset, z)
      scale.set(scaleValue, scaleValue, scaleValue)
    }
    trees.add(tree)
    gameObjects.add(tree)
  }
    
  fun initialize(carPosition: Vec3, gameObjects: ArrayList<GameObject>) {
    farthestSpawnedZ = carPosition.z
    nearestSpawnedZ = carPosition.z
    
    for (z in (carPosition.z - Tree.SPAWN_DISTANCE_BEHIND).toInt()..(carPosition.z + Tree.SPAWN_DISTANCE_AHEAD).toInt() step spawnInterval.toInt()) {
      for (i in 0 until treesPerSpawn) {
        spawnTree(z.toFloat(), gameObjects)
      }
    }
    farthestSpawnedZ = carPosition.z + Tree.SPAWN_DISTANCE_AHEAD
    nearestSpawnedZ = carPosition.z - Tree.SPAWN_DISTANCE_BEHIND
  }
}