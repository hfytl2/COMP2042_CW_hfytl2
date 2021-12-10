# **Brick Destroy**

## Key Changes

### Refactoring activities:

---

- Fully converted to JavaFX instead of Swing in favour of its MVC friendliness, growing and more flexible toolkit as well as faster UI development with the use of FXML.
- Used FXML files in conjunction with controller classes for all menu interfaces to adhere to MVC pattern 
- Extracted properties and methods from the `Wall` class and split it into the `Game` and `Level` classes, following SOLID principles
- Created an abstract class (`Entity`) to allow classes with common properties and methods (`Brick`, `Ball`, `Paddle`) to extend from, following the Liskov substitution and dependency inversion principles.
- Removed redundant constants and methods in `Brick` class as they are not used.
- Extracted properties and methods from the `Player` class that are related to the in-game paddle entity to its own `Paddle` class so that there is a distinction between the player and the in-game entity that the player controls, improving encapsulation.
- Extracted the `Crack` subclass from the `Brick` class into its own `Crack` class, following the single-responsibility and open-closed principles.
- Added singleton design pattern to the `Game` and `Player` classes to enhance maintainability.
- Extracted methods relating to entity movements into its own `Movable` interface, following interface segregation principle.
- Extracted methods relating to entity collisions into its own `Collidable` interface, following interface segregation principle.
- Created a `Crackable` interface which defines methods for bricks that can have cracks, following interface segregation principle.

### Additions:

---

- Added a home menu screen with a background image relevant to the game, buttons to start the game, read the help instructions or exit the game.
- Added a help screen which display the instructions of the game and its controls.
- Added background music, button pressed sound effects, ball impact sound effects and ball lost sound effects.
- Modified ball impact with paddle behaviour to normalize the speed of the ball and allow players to have better control on the direction of the ball and express their skill at the game.
- Added a score system that calculates the score based on the number of bricks destroyed and the type of bricks destroyed.
- Added a permanent high score leaderboard that shows when the player loses all their balls or beats the game by destroying all the bricks.
- Added an additional brick type (concrete) that is a stronger cement brick to make use of the crack class more.
- Added 6 additional playable levels with both new and existing brick type combinations to provide more variations and scoring opportunities.
- Added paddle shrink as the player progresses through the levels to increase difficulty.