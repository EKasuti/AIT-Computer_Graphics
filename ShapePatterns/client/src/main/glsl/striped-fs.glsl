#version 300 es

precision highp float;

in vec4 modelPosition;
in vec3 color;

out vec4 fragmentColor; //#vec4# A four-element vector [r,g,b,a].; Alpha is opacity, we set it to 1 for opaque.; It will be useful later for transparency.

uniform struct {
  vec3 primaryColor; // base stripe color
  vec3 secondaryColor; // alternate stripe color
  float stripeWidth; // width of stripe bands
} stripe;

void main(void) {
  // 45 degree stripes
  float stripePattern = fract((modelPosition.x + modelPosition.y) / stripe.stripeWidth);

  if (stripePattern < 0.5) {
    fragmentColor = vec4(stripe.primaryColor, 1.0);
  } else {
    fragmentColor = vec4(stripe.secondaryColor, 1.0);
  }
}
