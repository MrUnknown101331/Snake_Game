# Java Snake Game

This is a classic Snake game implemented in Java using Swing for the graphical user interface. Players control a snake,
navigating it to eat apples and grow longer. The game ends if the snake collides with the walls (in Hard mode) or its
own body.

## Features

* **Classic Snake Gameplay:** Control the snake with arrow keys.
* **Difficulty Levels:** Choose between Easy (no collision), Medium (wraparound walls), and Hard (walls are lethal).
* **Score Tracking:** Keeps track of the number of apples eaten.
* **Game Over Screen:** Displays a "Game Over" message and a restart button.
* **Pause Functionality:** Pause and resume the game using the spacebar.
* **Enhanced Graphics:** Improved snake rendering with outlines and body segment connections.

## Getting Started

### Prerequisites

* Java Development Kit (JDK) 11 or later. You can download it
  from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html).

### How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/MrUnknown101331/Snake_Game.git
   ```
   
2. **Navigate to the project directory:**
   ```bash
   cd Snake_Game
   ```

3. **Compile the Java files:**
   ```bash
   javac *.java
   ```

4. **Run the game:**
   ```bash
   java Selector
   ```

## Controls

* **Arrow Keys:** Control the snake's direction (Up, Down, Left, Right).
* **Spacebar:** Pause and resume the game.

## Game Logic and Implementation Details

* **`SnakePanel`:** This class handles the game logic, including snake movement, collision detection, apple generation,
  and drawing the game elements.
* **`ScorePanel`:**  This class manages the score display.
* **Difficulty:** Implemented by adjusting the game speed (`DELAY`) and enabling/disabling wall collisions.
* **Snake Rendering:** The snake is drawn using filled rectangles with outlines. The outlines change slightly to give
  the impression of connections between body segments.
* **Collision Detection:** Checks for collisions with the snake's body and the walls based on the difficulty level.

## Future Enhancements

* Sound effects.
* More visually appealing graphics and themes.
* Power-ups or special items.
* High score tracking and saving.

## Contributing

Contributions are welcome! If you'd like to contribute to this project, please:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes.
4. Submit a pull request.