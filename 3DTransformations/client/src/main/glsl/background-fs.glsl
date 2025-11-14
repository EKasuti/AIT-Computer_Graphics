#version 300 es
precision highp float;

out vec4 fragmentColor;
in vec4 rayDir;

uniform struct {
  samplerCube envTexture;
  float zoom;
  vec3 carPos;
} material;

void main(void) {
  float sidewaysMovementFactor = 0.08;
  float forwardMovementFactor = 0.02;
  vec2 movementOffset = material.carPos.xz * vec2(sidewaysMovementFactor, forwardMovementFactor);

  vec3 dir = vec3(
    (rayDir.x + movementOffset.x) * material.zoom,
    rayDir.y * material.zoom,
    rayDir.z + movementOffset.y
  );

  vec3 finalDir = normalize(dir);
  
  // Sunset colors
  vec3 skyTop = vec3(0.1, 0.2, 0.5); // Deep blue
  vec3 skyMid = vec3(0.8, 0.5, 0.7); // Purple-pink
  vec3 horizonColor = vec3(1.0, 0.6, 0.3); // Orange
  vec3 horizonGlow = vec3(1.0, 0.8, 0.5); // Yellow glow
  vec3 groundColor = vec3(0.2, 0.15, 0.25); // Dark purple ground
  
  float h = dir.y;
  
  // Sun position
  vec3 sunDir = normalize(vec3(0.3, 0.15, 1.0));
  float sunDist = distance(dir, sunDir);
  float sun = smoothstep(0.15, 0.0, sunDist);
  float sunGlow = smoothstep(0.4, 0.0, sunDist) * 0.3;
  
  vec3 color;
  if (h > 0.3) {
    color = mix(skyMid, skyTop, smoothstep(0.3, 1.0, h));
  } else if (h > 0.0) {
    float t = h / 0.3;
    color = mix(horizonColor, skyMid, t);
    color = mix(color, horizonGlow, (1.0 - t) * 0.5);
  } else if (h > -0.05) {
    color = mix(horizonColor, groundColor, smoothstep(0.0, -0.05, h));
  } else {
    color = groundColor;
  }
  
  // Add sun
  color = mix(color, vec3(1.0, 0.95, 0.8), sun);
  color += horizonGlow * sunGlow;
  
  fragmentColor = vec4(color, 1.0);
}
