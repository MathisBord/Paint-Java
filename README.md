# Java Vector Paint Application

## Overview

This project is a vector-based drawing application built using Java and the Swing GUI toolkit. It aims to replicate the core functionalities of classic drawing software like MS Paint while relying heavily on Object-Oriented Programming (OOP) principles. Because the application uses a vector-based approach, every drawn element is stored as an independent mathematical object rather than a collection of flat pixels.

<img width="1920" height="1032" alt="Capture d&#39;écran 2026-05-12 235834" src="https://github.com/user-attachments/assets/28710b50-433f-4465-a4f4-4753fd06520a" />

## Architecture and Structure

The codebase is strictly separated into two main packages, enforcing a clear distinction between the data logic (Model) and the user interface (View/Control).

### The `model` Package
This package contains the mathematical and logical definitions of everything that can be drawn on the screen.
- **`Shape` (Base Class)**: The foundation of the drawing system. It implements `java.io.Serializable` and defines generic properties like color. 
- **Shape Subclasses**: Tools like `Line`, `Rectangle`, `Circle`, `Triangle`, `EquilateralTriangle`, and `Truck` extend the `Shape` class. By overriding the `draw(Graphics g)` method, each shape knows exactly how to render itself.
- **`Pencil` and `Eraser`**: These also extend `Shape` but represent continuous freehand strokes. They store a `List<Point>` corresponding to the mouse drag path.
- **`Point`**: A custom, serializable class representing 2D coordinates (x, y).
- **Enumerations**: `EnumShape` and `Enumcolor` act as strict definitions for the available tools and colors in the application.

### The `viewcontrol` Package
This package handles the Swing graphical components and user interactions (mouse events, button clicks).
- **`Window`**: The main `JFrame` that acts as the container. It also holds the `JMenuBar` for file operations.
- **`TopBar`**: A `JPanel` containing interactive components (`JComboBox` for colors and shapes, `JButton` for Undo, Redo, Clear, and Eraser toggle). It listens to user actions and updates the application state.
- **`BottomBar`**: A `JPanel` dedicated to displaying real-time information, primarily the current X and Y coordinates of the user's mouse cursor.
- **`GraphicalArea`**: The core interactive canvas extending `JPanel`. It implements `MouseListener` and `MouseMotionListener`. It holds two vital data structures: a `List<Shape> storage` for currently drawn shapes, and a `List<Shape> redoStorage` for the undo/redo history. The `paintComponent` method iterates over the `storage` list to draw the entire scene.

## Key Features

### Vector-Based Rendering
Because every stroke or geometric shape is an instantiated object, the canvas can be completely cleared and redrawn instantly. This ensures that overlapping shapes maintain their individual properties and can be cleanly manipulated by history states.

### Freehand Drawing (Pencil) and Eraser
- **Pencil**: Captures the mouse's starting point upon a click, and dynamically adds new points to the `Pencil` object's internal list as the mouse is dragged.
- **Eraser**: Operates using the exact same logic as the Pencil, but specifically draws thick white strokes. Additionally, it features a dynamic preview circle attached to the cursor to indicate the precise erasing radius before clicking.

### Undo and Redo System
The application maintains a complete action history.
- **Undo**: Removes the last `Shape` added to the `storage` list and pushes it into the `redoStorage` stack.
- **Redo**: Pops the top `Shape` from `redoStorage` and places it back into the main `storage`.
- **Safety**: Whenever a *new* action is performed (e.g., drawing a new line), the `redoStorage` is immediately cleared to prevent timeline conflicts.

### Project Serialization (Save / Load)
Users can save their current work in an editable state. Because the `Shape` class and its dependencies implement the `Serializable` interface, the entire `storage` list is written directly to a binary `.dat` file using an `ObjectOutputStream`. Loading simply deserializes this file back into memory, fully restoring the vector shapes and allowing the user to continue modifying them.

### Standard Image Export (PNG)
In addition to saving the editable project, the application allows users to flatten and export the canvas to a standard `.png` image. It achieves this by creating an in-memory `BufferedImage`, instructing the `GraphicalArea` to paint itself onto that image's graphics context, and writing the result to the disk using `ImageIO`.

## Installation and Usage

### Download Pre-built Binaries
You do not need to install Java or compile the code yourself. Pre-built standalone executables are automatically generated for every major platform via GitHub Actions.
1. Go to the **Releases** tab on the right side of the GitHub repository page.
2. Download the appropriate file for your Operating System:
   - **Windows**: Download `Paint-*.exe` and run the installer.
   - **macOS**: Download `Paint-*.dmg`, open it, and drag the app to your Applications folder.
   - **Linux**: Download `Paint-*.deb` and install it via your package manager.
   - **Universal**: Download `Paint.jar` and double-click it (requires Java to be installed).

### Compiling from Source
If you prefer to run the application from source:

#### Prerequisites
- Java Development Kit (JDK) 8 or higher installed on your system.

### Compiling and Running
If you are using an IDE (like IntelliJ IDEA, Eclipse, or VS Code), you can simply open the project folder and run the `Main.java` file.

To compile and run manually via the command line:
1. Navigate to the `src` directory.
2. Compile the files:
   ```bash
   javac model/*.java viewcontrol/*.java Main.java -d ../out
   ```
3. Run the application:
   ```bash
   java -cp ../out Main
   ```

### How to Use
1. **Selecting Tools**: Use the top-left dropdown menus to select your desired color and geometric shape (or Pencil).
2. **Drawing**: Click and drag your mouse across the main white canvas to draw.
3. **Erasing**: Click the "Enable Eraser" button in the top bar, then drag over your drawings to erase them. Click "Disable Eraser" to return to normal drawing mode.
4. **History**: Use the "undo" and "redo" buttons in the top bar to step backward and forward through your actions.
5. **File Management**: Click on `File` in the top menu bar to access:
   - **Save**: Save your editable vector project (e.g., `drawing.dat`).
   - **Load**: Open a previously saved project.
   - **Export to PNG...**: Save your canvas as a flattened image file.
   - **Close**: Exit the application.
