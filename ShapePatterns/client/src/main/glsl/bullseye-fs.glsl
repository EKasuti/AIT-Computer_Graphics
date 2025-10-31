#version 300 es
precision highp float;

in vec4 modelPosition; // position in *model* space (from the vertex shader)
in vec3 color; // (not used here, but kept for consistency)

out vec4 fragmentColor;

// bullseye params
uniform struct {
  vec3 primaryColor;
  vec3 secondaryColor;
  float bandWidth;
} bullseye;

void main(void) {
  // distance from (0,0) in model space -> concentric rings around object origin
  float r = length(modelPosition.xy);

  // which ring index are we in?
  float bandIndex = floor(r / max(bullseye.bandWidth, 0.0001));
  // 0.0 for even, 1.0 for odd
  float odd = mod(bandIndex, 2.0);

  vec3 rgb = mix(bullseye.primaryColor, bullseye.secondaryColor, odd);
  fragmentColor = vec4(rgb, 1.0);
}
