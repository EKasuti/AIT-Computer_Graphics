#version 300 es

precision highp float;

in vec4 texCoord;
in vec4 worldNormal;
in vec4 worldPosition;

uniform struct {
  sampler2D colorTexture; 
} material;

uniform struct {
  vec4 position;
  vec3 powerDensity;
  vec3 direction;
  float cosSpotCutoff;
  float spotExponent;
} lights[8];

out vec4 fragmentColor;

uniform struct {
  mat4 viewProjMatrix;
  vec3 position;
} camera;

vec3 shade(
  vec3 normal, vec3 lightDir, vec3 viewDir,
  vec3 powerDensity, vec3 materialColor) {

  // Diffuse (Lambertian)
  float cosa = clamp(dot(lightDir, normal), 0.0, 1.0);
  vec3 diffuse = powerDensity * materialColor * cosa;

  // Specular (Phong-Blinn)
  vec3 halfway = normalize(lightDir + viewDir);
  float cosb = clamp(dot(normal, halfway), 0.0, 1.0);
  float shininess = 50.0;
  vec3 specular = powerDensity * pow(cosb, shininess);

  return diffuse + specular;
}

void main(void) {
  vec3 normal = normalize(worldNormal.xyz);
  vec3 viewDir = normalize(camera.position - worldPosition.xyz);

  fragmentColor.rgb = vec3(0.0, 0.0, 0.0);

  for (int i = 0; i < 8; i++) {
    vec3 lightDiff = lights[i].position.xyz - worldPosition.xyz * lights[i].position.w;
    vec3 lightDir = normalize(lightDiff);
    float distanceSquared = dot(lightDiff, lightDiff); 
    vec3 powerDensity = lights[i].powerDensity / distanceSquared;

    if(lights[i].cosSpotCutoff > 0.0) {
       float cosAngle = dot(-lightDir, normalize(lights[i].direction));
       if(cosAngle < lights[i].cosSpotCutoff) {
         powerDensity = vec3(0.0);
       } else {
         powerDensity *= pow(cosAngle, lights[i].spotExponent);
       }
    }

    fragmentColor.rgb += shade(normal, lightDir, viewDir, powerDensity, 
                                texture(material.colorTexture, texCoord.xy/texCoord.w).rgb);
  }

  fragmentColor.w = 1.0;
}