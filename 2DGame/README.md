# 2D Space Game

This project is a 2D game inspired by Asteroids

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
- When the avatar collides with a diamond, it’s collected and removed from the world.  
- Collected diamonds appear in the **upper-right corner of the screen**, sticking to the viewport even as the camera moves.  

---

## Installation
1. Clone the repository:
   ```
   git clone https://github.com/EKasuti/AIT-Computer_Graphics.git
   cd 2DGame
   ```
2. Build with gradle:
   ```
   gradle build
   ```
3. Run it locally with a simple server:
   ```
   npx http-server
   // or
   http-server
   ```

---