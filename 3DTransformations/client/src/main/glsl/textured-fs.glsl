#version 300 es

precision highp float;

out vec4 fragmentColor;

in vec2 texCoord; // pass this on from vertex shader
in vec3 normal;
in vec3 eye;
in vec3 modelPosition;
in vec3 worldPosition;

vec3 noiseGrad(vec3 r) {
  uvec3 s = uvec3(
    0x1D4E1D4E,
    0x58F958F9,
    0x129F129F);
  vec3 f = vec3(0, 0, 0);
  for(int i=0; i<16; i++) {
    vec3 sf =
    vec3(s & uvec3(0xFFFF))
  / 65536.0 - vec3(0.5, 0.5, 0.5);

    f += cos(dot(sf, r)) * sf;
    s = s >> 1;
  }
  return f;
}

// we need to bind texture to this
uniform struct{
  sampler2D colorTexture;
} material;

void main(void) {
  vec3 noise = noiseGrad(modelPosition.xyz * 50.0) * 0.05;
  vec3 normalPertubed = normal + noise;
  vec4 finalColour = texture(material.colorTexture, texCoord);
  if (finalColour.a < 0.01) {
    discard;
  }
  fragmentColor = texture(material.colorTexture, texCoord);
}

