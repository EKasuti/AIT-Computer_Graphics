## GameObjectModel


### Mouse commands
| Action | Description |
|---------|--------------|
| **Left Click**    | Select a triangle by clicking near its center. |
| **Shift + Left Click**    | Add/remove a triangle to/from the current selection (multi-select). |
| **Click Outside** | Deselects any selected triangle.               |
| **Right / Middle Click + Drag** | Pans (moves) the camera view.               |
| **Mouse Scroll** | Zoom in or out.               |


> Selection is highlighted in **yellow** for clarity.

---
### Keyboard commands

| Key | Action |
|-------|-----------------------|
| **W** | Move the camera up    |
| **A** | Move the camera left  |
| **S** | Move the camera down  |
| **D** | Move the camera right |
| **A (hold)** | Rotate selected triangles counter-clockwise  |
| **D (hold)** | Rotate selected triangles clockwise  |
| **Q** | Roll camera clockwise |
| **E** | Roll camera counterclockwise |
| **G** | Arrange objects on a regular grid |
| **Z** | Zoom In |
| **X** | Zoom Out |
| **Esc** | Deselect all |
| **Delete** | Delete a selected object |
| **Arrow Keys** | Move the **selected triangle** up / down / left / right |

---

## Installation
1. Clone the repository:
   ```
   git clone https://github.com/EKasuti/AIT-Computer_Graphics.git
   cd GameObjectModel
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