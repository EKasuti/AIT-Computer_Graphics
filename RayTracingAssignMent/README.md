# Ray Tracing Assignment

## Implemented Features
- [x] Shadows
  - Two abstract light sources (white, positioned on right side - top and bottom)

- [x] Silver baubles and golden bells
  - Two silver spheres with metallic environment reflection
  - Two golden spheres with metallic environment reflection

- [x] Snowman
  - Three white diffuse spheres (body - sizes: 2.5, 2.0, 1.5)
  - Two black coal eyes (small spheres)
  - Orange cone nose with diffuse lighting

- [x] A pile of oranges
  - Four orange diffuse spheres piled up

- [x] Fir
  - Stylized evergreen made of three clipped cones (foliage) with diffuse green color
  - Brown cylinder stem (trunk) with diffuse brown color

- [x] Box of presents
  - Two axis-aligned cuboids (Red and Blue) placed under the fir tree
  - Implemented using orthogonal clipped slabs (intersection of 3 slab-pairs)

- [x] Wooden floor
  - Infinite plane (approximated by large sphere) of polished wood
  - Diffuse reflection with procedural wood grain pattern (rings/lines)
  - Ideal reflection (environment mapping mixture)
  - Receives shadows from all objects

- [x] Candle
  - Wax cylinder and Flame sphere
  - Added 3rd light source (local point light) at flame position
  
- [x] Lights
  - 3 point lights (Top-Right, Bottom-Right, Candle-Flame)
  - Integrated into shader for diffuse and shadow calculations

- [x] Icicles
  - Three paraboloids hanging down above the tree
  - Refractive (transparent) material with slight blue tint

- [x] Animation
  - Golden bells sway back and forth in the wind

- [ ] Scissored

- [ ] A bowl of punch