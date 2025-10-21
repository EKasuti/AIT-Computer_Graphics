#version 300 es

precision highp float;

in vec4 texCoord;

uniform struct {
  sampler2D colorTexture;
  vec2 textureScale;
} material;

out vec4 fragmentColor;

void main(void) {
  fragmentColor = texture(material.colorTexture, texCoord.xy); 
}