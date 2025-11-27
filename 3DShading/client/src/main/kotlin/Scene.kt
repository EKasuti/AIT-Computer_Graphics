import org.w3c.dom.HTMLCanvasElement
import org.khronos.webgl.WebGLRenderingContext as GL //# GL# we need this for the constants declared ˙HUN˙ a constansok miatt kell
import kotlin.js.Date
import vision.gears.webglmath.UniformProvider
import vision.gears.webglmath.Vec1
import vision.gears.webglmath.Vec2
import vision.gears.webglmath.Vec3
import vision.gears.webglmath.Vec4
import vision.gears.webglmath.Mat4
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.cos

class Scene (
  val gl : WebGL2RenderingContext)  : UniformProvider("scene") {

  val vsTextured = Shader(gl, GL.VERTEX_SHADER, "textured-vs.glsl")
  val vsQuad = Shader(gl, GL.VERTEX_SHADER, "quad-vs.glsl")
  val fsTextured = Shader(gl, GL.FRAGMENT_SHADER, "textured-fs.glsl")
  val fsBackground = Shader(gl, GL.FRAGMENT_SHADER, "background-fs.glsl")
  val fsEnvmapped = Shader(gl, GL.FRAGMENT_SHADER, "envmapped-fs.glsl")

  val texturedProgram = Program(gl, vsTextured, fsTextured)
  val backgroundProgram = Program(gl, vsQuad, fsBackground)
  val envmappedProgram = Program(gl, vsTextured, fsEnvmapped)

  val texturedQuadGeometry = TexturedQuadGeometry(gl)
  val groundGeometry = GroundGeometry(gl)

  val gameObjects = ArrayList<GameObject>()

  val envTexture = TextureCube(gl,
    "media/posx512.jpg", "media/negx512.jpg",
    "media/posy512.jpg", "media/negy512.jpg",
    "media/posz512.jpg", "media/negz512.jpg"
  )  

  val jsonLoader = JsonLoader()
  val slowpokeMeshes = jsonLoader.loadMeshes(gl,
    "media/slowpoke/slowpoke.json",
    Material(texturedProgram).apply{
      this["colorTexture"]?.set(
          Texture2D(gl, "media/slowpoke/YadonDh.png"))
    },
    Material(texturedProgram).apply{
      this["colorTexture"]?.set(
          Texture2D(gl, "media/slowpoke/YadonEyeDh.png"))
    }
  )

  val envmapeddSlowpokeMeshes = jsonLoader.loadMeshes(gl,
    "media/slowpoke/slowpoke.json",
    Material(envmappedProgram).apply{
      this["envTexture"]?.set(envTexture)
    },
    Material(envmappedProgram).apply{
      this["envTexture"]?.set(envTexture)
    }
  )

  val backgroundMaterial = Material(backgroundProgram)
  val backgroundMesh = Mesh(backgroundMaterial, texturedQuadGeometry)

  val groundMaterial = Material(texturedProgram).apply{
    this["colorTexture"]?.set(Texture2D(gl, "media/pattern.jpg"))
  }
  val groundMesh = Mesh(groundMaterial, groundGeometry)

  init{
    backgroundMaterial["envTexture"]?.set( this.envTexture )

    gameObjects += GameObject(*slowpokeMeshes)
    gameObjects += GameObject(backgroundMesh)
    gameObjects += GameObject(groundMesh)
  }

  val lights = Array<Light>(8) { Light(it) }
  init{
    lights[0].position.set(1.0f, 1.0f, 1.0f, 0.0f).normalize();
    lights[0].powerDensity.set(1.0f, 1.0f, 1.0f);
    
    lights[1].position.set(10.0f, 10.0f, 10.0f, 1.0f);
    lights[1].powerDensity.set(1.0f, 0.0f, 1.0f);

    lights[2].position.set(0.0f, 0.0f, 0.0f, 1.0f);
    lights[2].powerDensity.set(50.0f, 50.0f, 50.0f); // Strong headlight
    lights[2].cosSpotCutoff.set(0.8f);
    lights[2].spotExponent.set(5.0f);

    lights[3].position.set(15.0f, 20.0f, 0.0f, 1.0f); // Stationary spotlight from above
    lights[3].direction.set(0.0f, -1.0f, 0.0f); // Pointing down
    lights[3].powerDensity.set(100.0f, 80.0f, 60.0f); // Warm spotlight
    lights[3].cosSpotCutoff.set(0.9f); // Narrower beam
    lights[3].spotExponent.set(15.0f); // More focused
  }


  // LABTODO: replace with 3D camera
  val camera = PerspectiveCamera().apply{
    position.set(0.0f, 10.0f, 20.0f)
    pitch = -0.5f
    update()
  }

  fun resize(canvas : HTMLCanvasElement) {
    gl.viewport(0, 0, canvas.width, canvas.height)//#viewport# tell the rasterizer which part of the canvas to draw to ˙HUN˙ a raszterizáló ide rajzoljon
    camera.setAspectRatio(canvas.width.toFloat()/canvas.height)
  }

  val timeAtFirstFrame = Date().getTime()
  var timeAtLastFrame =  timeAtFirstFrame

  init{
    //LABTODO: enable depth test
    gl.enable(GL.DEPTH_TEST)
  }

  @Suppress("UNUSED_PARAMETER")
  fun update(keysPressed : Set<String>) {
    val timeAtThisFrame = Date().getTime() 
    val dt = (timeAtThisFrame - timeAtLastFrame).toFloat() / 1000.0f
    val t = (timeAtThisFrame - timeAtFirstFrame).toFloat() / 1000.0f
    timeAtLastFrame = timeAtThisFrame

    //LABTODO: move camera
    camera.move(dt, keysPressed)
    
    // Light 1: Moving Point Light
    lights[1].position.set(sin(t)*10.0f, 5.0f, cos(t)*10.0f, 1.0f)
    
    // Light 2: Headlight
    lights[2].position.set(camera.position.x, camera.position.y, camera.position.z, 1.0f)
    lights[2].direction.set(camera.ahead)
    
    gl.clearColor(0.3f, 0.0f, 0.3f, 1.0f)//## red, green, blue, alpha in [0, 1]
    gl.clearDepth(1.0f)//## will be useful in 3D ˙HUN˙ 3D-ben lesz hasznos
    gl.clear(GL.COLOR_BUFFER_BIT or GL.DEPTH_BUFFER_BIT)//#or# bitwise OR of flags

    gl.enable(GL.BLEND)
    gl.blendFunc(
      GL.SRC_ALPHA,
      GL.ONE_MINUS_SRC_ALPHA)

    gameObjects.forEach{ it.move(dt, t, keysPressed, gameObjects) }

    gameObjects.forEach{ it.update() }
    gameObjects.forEach{ it.draw(this, camera, *lights) }
  }
}
