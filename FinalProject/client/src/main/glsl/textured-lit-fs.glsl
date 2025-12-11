#version 300 es

precision highp float;

in vec4 texCoord;
in vec4 worldPosition;

uniform struct {
  sampler2D colorTexture;
  vec2 textureScale;
} material;

// Dynamic lighting system
uniform struct {
  vec4 position[8]; // Light positions in world space
  vec3 powerDensity[8]; // Light colors/intensities
  int numLights; // Number of active lights
} lights;

out vec4 fragmentColor;

void main(void) {
  vec4 baseColor = texture(material.colorTexture, texCoord.xy);

  // Start with ambient lighting
  vec3 ambient = vec3(0.3, 0.3, 0.4); // Slight blue ambient for space
  vec3 finalColor = baseColor.rgb * ambient;

  // Add contribution from each point light
  for(int i = 0; i < lights.numLights; i++) {
    vec3 lightDir = lights.position[i].xyz - worldPosition.xyz;
    float distance = length(lightDir);

    // Inverse square falloff with minimum distance to avoid division by zero
    float attenuation = 1.0 / max(distance * distance, 0.1);

    // Add light contribution
    vec3 lightContribution = lights.powerDensity[i] * attenuation;
    finalColor += baseColor.rgb * lightContribution;
  }

  fragmentColor = vec4(finalColor, baseColor.a);
}
