#version 300 es 
precision highp float;

out vec4 fragmentColor;
in vec4 rayDir;

uniform struct {
  vec3 position;
  mat4 rayDirMatrix;
} camera;

uniform struct {
	samplerCube envTexture;
} material;

float intersectQuadric(mat4 A, vec4 e, vec4 d){
  float a = dot(d * A, d);
  float b = dot(e * A, d) + dot(d * A, e);
  float c = dot(e * A, e);

  float disc = b * b - 4.0 * a * c;
  if(disc < 0.0) return -1.0;

  float sqrtDisc = sqrt(disc);
  float t1 = (-b - sqrtDisc) / (2.0 * a);
  float t2 = (-b + sqrtDisc) / (2.0 * a);

  return (t1<0.0)?t2:((t2<0.0)?t1:min(t1, t2));
}
void main(void) {
  vec4 e = vec4(camera.position, 1.0);
  vec4 d = vec4(normalize(rayDir.xyz), 0.0);

  mat4 A = mat4(	1, 0, 0, 0,
		0, 1, 0, 0,
		0, 0, 1, 0,
		0, 0, 0, -9	);

  float t = intersectQuadric(A, e, d);
  

  if (t < 0.0){
    fragmentColor = texture(material.envTexture, rayDir.xyz);
  } else {
    fragmentColor = vec4(0, 0, 1, 1);
  }
  
}
