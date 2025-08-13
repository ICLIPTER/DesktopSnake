# 🐍 Desktop Snake Game (Java)

A fun transparent Snake game that runs **directly on your desktop** — no borders, no background, just the snake slithering around your screen.  
Use your arrow keys to control the snake and eat the red apples to grow!

---

## ✨ Features
- **Transparent overlay** — Snake appears on top of all windows.
- **Full-screen play** — Uses your actual desktop resolution.
- **Smooth 60 FPS** — Optimized with GPU acceleration.
- **Borderless** — Snake wraps around screen edges.
- **Simple controls** — Arrow keys to move, ESC to quit.

---

## 🖥️ How It Works
This project uses:
- **Java Swing + Canvas + BufferStrategy** for smooth graphics.
- **AlphaComposite** for transparent rendering.
- **`Toolkit.getScreenSize()`** to detect your desktop resolution.

---

## 🎮 Controls
| Key | Action |
|-----|--------|
| ⬅️  | Move Left |
| ➡️  | Move Right |
| ⬆️  | Move Up |
| ⬇️  | Move Down |
| `ESC` | Quit the game |

---

## 📦 Requirements
- **Java 8+** (Java 17+ recommended)
- Works on Windows, macOS, and Linux.

---

## 🚀 How to Run
1. Save the code as `DesktopSnakeFast.java`.
2. Compile:
   ```bash
   javac DesktopSnakeFast.java
