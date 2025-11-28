import vision.gears.webglmath.UniformProvider
import vision.gears.webglmath.Vec3
import vision.gears.webglmath.Vec4

class Light(i : Int) : UniformProvider("lights[${i}]") {
  val position by Vec4(0.0f, 0.0f, 0.0f, 1.0f)
  val powerDensity by Vec3(0.0f, 0.0f, 0.0f)
}