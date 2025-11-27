import vision.gears.webglmath.UniformProvider
import vision.gears.webglmath.Vec3
import vision.gears.webglmath.Vec4
import vision.gears.webglmath.Vec1

class Light(id : Int) : UniformProvider("lights[$id]") {

  val position by Vec4(0.0f, 1.0f, 0.0f, 0.0f) 
  val powerDensity by Vec3(0.0f, 0.0f, 0.0f)
  val direction by Vec3(0.0f, 0.0f, -1.0f)
  val cosSpotCutoff by Vec1(-1.0f) // less than 0 means no spot
  val spotExponent by Vec1(10.0f)
}
