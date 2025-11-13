#version 300 es
precision highp float;

out vec4 fragmentColor;
in vec4 rayDir;

uniform struct {
  samplerCube envTexture;
  float zoom;
} material;

void main(void) {
  vec3 dir = vec3(
    rayDir.x * material.zoom,
    rayDir.y * material.zoom,
    rayDir.z
  );

  vec3 finalDir = normalize(dir);

  fragmentColor = texture(material.envTexture, finalDir);
}
