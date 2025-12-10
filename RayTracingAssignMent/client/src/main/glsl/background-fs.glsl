#version 300 es 
precision highp float;

out vec4 fragmentColor;
in vec4 rayDir;

uniform struct {
	samplerCube envTexture;
} material;

uniform struct {
  vec3 position;
  mat4 rayDirMatrix;
} camera;

uniform struct {
  mat4 surface;
  mat4 clipper;
  mat4 clipper2;
} quadrics[32];

// Light sources for shadows
uniform vec3 lightPositions[3];
uniform vec3 lightColors[3];

float intersectQuadric(vec4 e, vec4 d, mat4 A, mat4 B, mat4 C) {

	float a = dot(d * A, d);
	float b = dot(d * A, e) + dot(e * A, d);
	float c = dot(e * A, e);

	float D = b * b - 4.0 * a * c;
	if (D < 0.0)
	{
		return -1.0;
	}

	D = sqrt (D);

	float t1 = (-b + D) / (2.0 * a);
	float t2 = (-b - D) / (2.0 * a);

	vec4 hit1 = e + d * t1;
	if (dot(hit1 * B, hit1) < 0.0 || dot(hit1 * C, hit1) < 0.0)
	{
		t1 = -1.0;
	}

	vec4 hit2 = e + d * t2;
	if (dot(hit2 * B, hit2) < 0.0 || dot(hit2 * C, hit2) < 0.0)
	{
		t2 = -1.0;
	}


	return (t1<0.0)?t2:((t2<0.0)?t1:min(t1, t2));
}

// Snowman Rendering
vec4 renderSnowmanBody(int quadricIdx, vec4 hit, vec3 normal) {
	// Diffuse lighting from both lights
	vec3 toLight1 = normalize(lightPositions[0] - hit.xyz);
	vec3 toLight2 = normalize(lightPositions[1] - hit.xyz);
	float diffuse = max(dot(normal, toLight1), 0.0) + max(dot(normal, toLight2), 0.0);

	vec3 snowColor = vec3(1.0, 1.0, 1.0) * (0.3 + diffuse * 0.7);
	return vec4(snowColor, 1.0);
}

vec4 renderSnowmanEyes() {
	return vec4(0.1, 0.1, 0.1, 1.0); // Black coal
}

vec4 renderSnowmanNose(vec4 hit, vec3 normal) {
	// Diffuse lighting from both lights
	vec3 toLight1 = normalize(lightPositions[0] - hit.xyz);
	vec3 toLight2 = normalize(lightPositions[1] - hit.xyz);
	float diffuse = max(dot(normal, toLight1), 0.0) + max(dot(normal, toLight2), 0.0);

	vec3 orangeColor = vec3(1.0, 0.5, 0.0) * (0.3 + diffuse * 0.7);
	return vec4(orangeColor, 1.0);
}

vec4 renderSilverBauble(vec4 hit, vec3 normal, vec3 rayDir) {
	vec3 reflectedDir = reflect(rayDir, normal);
	vec3 envColor = texture(material.envTexture, reflectedDir).rgb;
	// Silver tint
	return vec4(envColor * vec3(0.95, 0.95, 0.95), 1.0);
}

vec4 renderGoldenBell(vec4 hit, vec3 normal, vec3 rayDir) {
	vec3 reflectedDir = reflect(rayDir, normal);
	vec3 envColor = texture(material.envTexture, reflectedDir).rgb;
	// Gold tint
	return vec4(envColor * vec3(1.0, 0.84, 0.0), 1.0);
}

vec4 renderTreeTrunk(vec4 hit, vec3 normal) {
	// Diffuse lighting from both lights
	vec3 toLight1 = normalize(lightPositions[0] - hit.xyz);
	vec3 toLight2 = normalize(lightPositions[1] - hit.xyz);
	float diffuse = max(dot(normal, toLight1), 0.0) + max(dot(normal, toLight2), 0.0);

	vec3 brownColor = vec3(0.4, 0.2, 0.1) * (0.3 + diffuse * 0.7);
	return vec4(brownColor, 1.0);
}

vec4 renderTreeFoliage(vec4 hit, vec3 normal) {
	// Diffuse lighting from both lights
	vec3 toLight1 = normalize(lightPositions[0] - hit.xyz);
	vec3 toLight2 = normalize(lightPositions[1] - hit.xyz);
	float diffuse = max(dot(normal, toLight1), 0.0) + max(dot(normal, toLight2), 0.0);

	vec3 greenColor = vec3(0.1, 0.5, 0.2) * (0.3 + diffuse * 0.7);
	return vec4(greenColor, 1.0);
}

float computeShadow(vec3 hit, vec3 normal, vec3 lightPos) {
    vec3 toLight = lightPos - hit;
    vec3 lightDir = normalize(toLight);
    float lightDist = length(toLight);
    
    for (int i=0; i<32; i++) {
        float t = intersectQuadric(vec4(hit + normal*0.001, 1.0), vec4(lightDir, 0.0), quadrics[i].surface, quadrics[i].clipper, quadrics[i].clipper2);
        if (t > 0.0 && t < lightDist) return 0.0; // In shadow
    }
    return 1.0;
}

vec4 renderWoodenFloor(vec4 hit, vec3 geometricNormal, vec3 viewDir) {
	vec3 normal = vec3(0.0, 1.0, 0.0); // Force flat normal for uniform lighting
	// Shadow & Diffuse
	float shadow1 = computeShadow(hit.xyz, normal, lightPositions[0]);
	float shadow2 = computeShadow(hit.xyz, normal, lightPositions[1]);

	vec3 toLight1 = normalize(lightPositions[0] - hit.xyz);
	vec3 toLight2 = normalize(lightPositions[1] - hit.xyz);
	float diffuse = max(dot(normal, toLight1), 0.0) * shadow1 + max(dot(normal, toLight2), 0.0) * shadow2;

	// Procedural Wood Grain Removed
	vec3 woodColor = vec3(0.6, 0.4, 0.2);
    
    // Ideal Reflection (Environment)
    vec3 reflectedDir = reflect(viewDir, normal);
    vec3 envColor = texture(material.envTexture, reflectedDir).rgb;

    // Mix Wood (Diffuse) and Reflection
	vec3 finalColor = mix(woodColor * (0.2 + diffuse * 0.8), envColor, 0.3);

	return vec4(finalColor, 1.0);
}

void main(void) {
	vec4 e = vec4(camera.position, 1);
	vec4 d = vec4(normalize(rayDir.xyz), 0);

	float bestT = 10000.0;
	int bestI = 0;

	for (int i = 0; i < 32; i++)
	{
		float tLocal = intersectQuadric (e, d, quadrics[i].surface, quadrics[i].clipper, quadrics[i].clipper2);
		if (tLocal < bestT && tLocal > 0.0)
		{
			bestT = tLocal;
			bestI = i;
		}
	}

	if (bestT > 0.0 && bestT < 10000.0) {
		// Light sphere (white)
		if (bestI == 0) {
			fragmentColor = vec4(1.0, 1.0, 1.0, 1.0);
			return;
		}

		// Snowman body (white diffuse)
		if (bestI >= 1 && bestI <= 3) {
			vec4 hit = e + d * bestT;
			vec3 normal = normalize((hit * quadrics[bestI].surface + quadrics[bestI].surface * hit).xyz);
			if (dot(normal, -d.xyz) < 0.0) normal *= -1.0;

			fragmentColor = renderSnowmanBody(bestI, hit, normal);
			return;
		}

		// Coal eyes (black diffuse)
		if (bestI == 4 || bestI == 5) {
			fragmentColor = renderSnowmanEyes();
			return;
		}

		// Orange cone nose AND Pile of Oranges
		if (bestI == 6 || (bestI >= 7 && bestI <= 10)) {
			vec4 hit = e + d * bestT;
			vec3 normal = normalize((hit * quadrics[bestI].surface + quadrics[bestI].surface * hit).xyz);
			if (dot(normal, -d.xyz) < 0.0) normal *= -1.0;

			fragmentColor = renderSnowmanNose(hit, normal);
			return;
		}

		// Silver Baubles (Metallic Reflection)
		if (bestI == 11 || bestI == 12) {
			vec4 hit = e + d * bestT;
			vec3 normal = normalize((hit * quadrics[bestI].surface + quadrics[bestI].surface * hit).xyz);
			if (dot(normal, -d.xyz) < 0.0) normal *= -1.0;

			fragmentColor = renderSilverBauble(hit, normal, d.xyz);
			return;
		}

		// Golden Bells (Metallic Reflection)
		if (bestI == 13 || bestI == 14) {
			vec4 hit = e + d * bestT;
			vec3 normal = normalize((hit * quadrics[bestI].surface + quadrics[bestI].surface * hit).xyz);
			if (dot(normal, -d.xyz) < 0.0) normal *= -1.0;

			fragmentColor = renderGoldenBell(hit, normal, d.xyz);
			return;
		}

		// Fir Tree Trunk (Brown diffuse)
		if (bestI == 15) {
			vec4 hit = e + d * bestT;
			vec3 normal = normalize((hit * quadrics[bestI].surface + quadrics[bestI].surface * hit).xyz);
			if (dot(normal, -d.xyz) < 0.0) normal *= -1.0;

			fragmentColor = renderTreeTrunk(hit, normal);
			return;
		}

		// Fir Tree Foliage (Green diffuse cones)
		if (bestI >= 16 && bestI <= 18) {
			vec4 hit = e + d * bestT;
			vec3 normal = normalize((hit * quadrics[bestI].surface + quadrics[bestI].surface * hit).xyz);
			if (dot(normal, -d.xyz) < 0.0) normal *= -1.0;

			fragmentColor = renderTreeFoliage(hit, normal);
			return;
		}

		// Wooden Floor (Procedural Wood)
		if (bestI == 19) {
			vec4 hit = e + d * bestT;
			vec3 normal = normalize((hit * quadrics[bestI].surface + quadrics[bestI].surface * hit).xyz);
			if (dot(normal, -d.xyz) < 0.0) normal *= -1.0;

			fragmentColor = renderWoodenFloor(hit, normal, d.xyz);
			return;
		}

		// Presents (Orthogonal Box Slabs)
		if (bestI >= 20 && bestI <= 25) {
			vec4 hit = e + d * bestT;
			vec3 normal = normalize((hit * quadrics[bestI].surface + quadrics[bestI].surface * hit).xyz);
			if (dot(normal, -d.xyz) < 0.0) normal *= -1.0;

			vec3 color = (bestI <= 22) ? vec3(0.8, 0.1, 0.1) : vec3(0.1, 0.2, 0.9); // Red or Blue

			// Keep inline simple lighting for presents
			vec3 toLight1 = normalize(lightPositions[0] - hit.xyz);
			vec3 toLight2 = normalize(lightPositions[1] - hit.xyz);
			float diffuse = max(dot(normal, toLight1), 0.0) + max(dot(normal, toLight2), 0.0);
			
			        fragmentColor = vec4(color * (0.3 + diffuse * 0.7), 1.0);
        return;
    }

    // Candle (Wax + Flame)
    if (bestI == 29 || bestI == 30) {
        // Wax
        if (bestI == 29) {
            vec4 hit = e + d * bestT;
            vec3 normal = normalize((hit * quadrics[bestI].surface + quadrics[bestI].surface * hit).xyz);
            if (dot(normal, -d.xyz) < 0.0) normal *= -1.0;
            
            vec3 waxColor = vec3(0.9, 0.8, 0.8);
            
            // Diffuse from all 3 lights
            float diffuse = 0.0;
            for(int l=0; l<3; l++) {
                vec3 toLight = normalize(lightPositions[l] - hit.xyz);
                float shadow = computeShadow(hit.xyz, normal, lightPositions[l]);
                diffuse += max(dot(normal, toLight), 0.0) * shadow; 
            }
            fragmentColor = vec4(waxColor * (0.2 + diffuse * 0.6), 1.0);
            return;
        }
        // Flame
        if (bestI == 30) {
            fragmentColor = vec4(1.0, 0.8, 0.2, 1.0); // Bright Yellow
            return;
        }
    }

		float t = bestT;
		mat4 A = quadrics[bestI].surface;
		vec4 hit = e + d * t;
		vec3 normal = normalize( (hit * A + A * hit).xyz );
		vec3 reflectedDir = reflect (d.xyz, normal);

		if (dot (normal, -d.xyz) < 0.0)
		{
			normal *= -1.0;
		}

		// Shadows (two light sources)
		float shadow1 = 1.0;
		float shadow2 = 1.0;

		// Light 1 shadow check
		vec3 toLight1 = lightPositions[0] - hit.xyz;
		vec3 lightDir1 = normalize(toLight1);
		for (int i = 0; i < 32; i++) {
			float tShadow = intersectQuadric(
				vec4(hit.xyz + normal * 0.001, 1.0),
				vec4(lightDir1, 0.0),
				quadrics[i].surface, quadrics[i].clipper, quadrics[i].clipper2);
			if (tShadow > 0.0 && tShadow < length(toLight1)) {
				shadow1 = 0.0; break;
			}
		}

		// Light 2 shadow check
		vec3 toLight2 = lightPositions[1] - hit.xyz;
		vec3 lightDir2 = normalize(toLight2);
		for (int i = 0; i < 32; i++) {
			float tShadow = intersectQuadric(
				vec4(hit.xyz + normal * 0.001, 1.0),
				vec4(lightDir2, 0.0),
				quadrics[i].surface, quadrics[i].clipper, quadrics[i].clipper2);
			if (tShadow > 0.0 && tShadow < length(toLight2)) {
				shadow2 = 0.0; break;
			}
		}

		e = vec4 (hit.xyz + normal * 0.0001, 1.0);
		d = vec4 (reflectedDir.xyz, 0.0);

		// Apply shadows to debug/fallback logic OR just rely on per-object shadows
		fragmentColor = texture(material.envTexture, d.xyz);
		return;
	}

	fragmentColor = texture (material.envTexture, d.xyz);
}
