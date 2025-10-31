#version 300 es
precision highp float;

in vec4 modelPosition;
in vec3 color;

out vec4 fragmentColor;

uniform struct {
  vec3 colorA;
  vec3 colorB;
  float squareSize;
} check;

void main(void) {
  // divide model space by the square size to get a grid
  float s = max(check.squareSize, 0.0001);
  vec2 uv = modelPosition.xy / s;

  // which cell are we in?
  float ix = floor(uv.x);
  float iy = floor(uv.y);

  // checker toggles every cell
  float m = mod(ix + iy, 2.0); // 0 or 1
  vec3 c = mix(check.colorA, check.colorB, m);

  fragmentColor = vec4(c, 1.0);
}
