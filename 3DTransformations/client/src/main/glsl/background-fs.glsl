#version 300 es
precision highp float;

out vec4 fragmentColor;
in vec4 rayDir;

uniform struct {
  samplerCube envTexture;
  float zoom;
  vec3 carPos;
} material;

void main(void) {
  float sidewaysMovementFactor = 0.08;
  float forwardMovementFactor = 0.02;
  vec2 movementOffset = material.carPos.xz * vec2(sidewaysMovementFactor, forwardMovementFactor);

  vec3 dir = vec3(
    (rayDir.x + movementOffset.x) * material.zoom,
    rayDir.y * material.zoom,
    rayDir.z + movementOffset.y
  );

  vec3 finalDir = normalize(dir);

  fragmentColor = texture(material.envTexture, finalDir);
}
