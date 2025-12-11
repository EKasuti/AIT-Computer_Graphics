#version 300 es

in vec4 vertexPosition; //#vec4# A four-element vector [x,y,z,w].; We leave z and w alone.; They will be useful later for 3D graphics and transformations. #vertexPosition# attribute fetched from vertex buffer according to input layout spec
in vec4 vertexTexCoord;

uniform struct{
  mat4 modelMatrix;
  vec2 textureOffset;
} gameObject;

uniform struct{
  mat4 viewProjMatrix; 
} camera;

uniform struct {
  float time;
} scene;

uniform struct {
  sampler2D colorTexture;
  vec2 textureScale;
} material;

out vec4 modelPosition;
out vec4 worldPosition;
out vec4 texCoord;

void main(void) {
  modelPosition = vertexPosition;
  gl_Position = vertexPosition * gameObject.modelMatrix * camera.viewProjMatrix;
  worldPosition = gl_Position;
  texCoord = vertexTexCoord;
//  texCoord = (vertexTexCoord + vec4(gameObject.textureOffset, 0, 0)) * vec4(material.textureScale, 1, 1);
}