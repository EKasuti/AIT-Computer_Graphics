# 2D Space Game - Final Project Enhancement

This project is a 2D game inspired by Asteroids, enhanced with advanced graphics techniques from Computer Graphics course projects.

## New Features (Final Project)

This enhanced version integrates techniques from 3DShading, RayTracing, and ShapePatterns projects to demonstrate:
- **Shading**: Dynamic multi-light system with distance attenuation
- **Vectors**: Physics-based particle system with velocity and gravity
- **Camera 3D**: Camera shake effects with exponential decay
- **Modeling**: Procedural particle geometry with instancing

---

## Implemented Features

### 1. Avatar Control — *Rocket Science*
The avatar (spaceship) can be accelerated by multiple thrusters using keyboard controls:
- **W** applies forward thrust.
- **S** applies reverse thrust.
- **A / D** rotate the ship left and right.  
- The avatar never stops instantly when thrust stops.

---

### 2. Collisions — *Landing*
Horizontal platforms act as landing surfaces.    
The avatar can rest, bounce slightly, and maintain contact naturally.

---

### 3. Texture Animation — *Boom*
When collisions occur (e.g., asteroid impact), an **explosion** is triggered.  

---

### 4. Parenting — *Flames*
Two exhaust flames are attached to the avatar as **child objects**, fixed relative to its local frame.  
When the player applies thrust, the flames become visible; when thrust stops, they are hidden.

---

### 5. Spawn — *Shoot ’em Up*
Pressing **SPACE** fires bullets in the avatar’s facing direction.  
Each shot uses the avatar’s current rotation to determine trajectory.  
A cooldown timer prevents continuous firing

---

### 6. AI — *Seeker*
Enemy **UFOs (seekers)** automatically accelerate toward the avatar’s position.  

---

### 7. Challenge — *Lucy in the Sky*
On Jupiter, it rains diamonds
- Diamonds continuously spawn above the avatar and fall downward.
- When the avatar collides with a diamond, it's collected and removed from the world.
- Collected diamonds appear in the **upper-right corner of the screen**, sticking to the viewport even as the camera moves.

---

## Enhanced Features (Final Project)

### 8. Dynamic Point Lighting — *Shading*
**Course Topic: Shading**

Implements a real-time multi-light system inspired by the 3DShading project:
- **Point lights** attached to game objects (bullets, explosions, seekers, diamonds)
- **Distance-based attenuation**: Inverse-square falloff for realistic light behavior
- **Color-coded lighting**:
  - Bullets: Bright white-blue glow (2.0, 2.0, 3.0)
  - Explosions: Intense orange flash (8.0, 4.0, 1.0)
  - Seekers: Menacing red glow (3.0, 0.3, 0.3)
  - Diamonds: Golden shimmer (3.0, 2.5, 0.5)
- **Ambient + Diffuse**: Blue ambient space lighting combined with dynamic point lights

**Technical Implementation**:
- New shader: [textured-lit-fs.glsl](client/src/main/glsl/textured-lit-fs.glsl) with lighting calculations
- Light array supports up to 8 simultaneous lights
- Uniform passing in [Scene.kt:331-341](client/src/main/kotlin/Scene.kt#L331-L341)

---

### 9. Physics-Based Particle System — *Vectors & Modeling*
**Course Topics: Vectors, Transformations, Modeling**

A complete particle system inspired by RayTracing's physics simulation:
- **Per-particle physics**: Each particle has position, velocity, lifetime
- **Vector operations**: Velocity accumulation, gravity application, normalization
- **Particle spawning**:
  - **Continuous emission**: Exhaust particles stream from avatar when thrusting (50 particles/sec)
  - **Burst emission**: 30 particles explode on asteroid collisions
- **Particle properties**:
  - Orange exhaust color (1.0, 0.6, 0.2)
  - Gravity: -5 units/sec²
  - Lifetime: 0.6 seconds with alpha fade
  - Random spread angle and velocity variation

**Technical Implementation**:
- [Particle.kt](client/src/main/kotlin/Particle.kt): Individual particle with physics
- [ParticleEmitter.kt](client/src/main/kotlin/ParticleEmitter.kt): Emitter managing particle pool
- Exhaust emitter position/direction updated from avatar rotation in [Scene.kt:200-207](client/src/main/kotlin/Scene.kt#L200-L207)
- Burst emission on collision in [Scene.kt:145-149](client/src/main/kotlin/Scene.kt#L145-L149)

---

### 10. Camera Shake — *Camera 3D*
**Course Topic: Camera**

Screen shake effect adds impact feedback to collisions:
- **Triggered on**: Asteroid-avatar collisions
- **Exponential decay**: Shake intensity decreases at 5 units/sec
- **Random offset**: Camera position jitters in 2D (x, y)
- **Smooth return**: Decays to zero smoothly for professional feel

**Technical Implementation**:
- Shake variables in [Scene.kt:97-104](client/src/main/kotlin/Scene.kt#L97-L104)
- Update logic in [Scene.kt:315-327](client/src/main/kotlin/Scene.kt#L315-L327)
- Applied to camera position before rendering in [Scene.kt:330](client/src/main/kotlin/Scene.kt#L330)

---

## Course Topics Demonstrated

| Feature | Course Topics | Implementation |
|---------|---------------|----------------|
| Dynamic Lighting | **Shading** | Distance-based light attenuation, multiple point lights |
| Particle System | **Vectors, Modeling** | Physics simulation, velocity/gravity, procedural geometry |
| Camera Shake | **Camera 3D** | View matrix manipulation, exponential decay |
| Original Game | **2D Spaces, Transformations** | Hierarchical transforms, collision detection |

---

## Installation & Running

1. Navigate to the project:
   ```bash
   cd FinalProject
   ```
2. Build with gradle:
   ```bash
   gradle build
   ```
3. Run with a local server:
   ```bash
   npx http-server
   ```
4. Open browser and navigate to:
   ```
   http://localhost:8080/build/dist/js/productionExecutable/
   ```

### Controls
- **W**: Thrust forward
- **S**: Thrust backward
- **A/D**: Rotate left/right
- **SPACE**: Shoot bullets
- **X**: Exit

**Tip**: Collide with asteroids to see dynamic lighting, particle explosions, and camera shake in action!

---