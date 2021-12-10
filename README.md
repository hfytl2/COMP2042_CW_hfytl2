# Brick Destroy
## Key Changes
### Refactoring activities:
- The entirety of the source files has been converted to use JavaFX instead of Swing in favour of its MVC friendliness, growing and more flexible toolkit as well as faster UI development with the use of FXML.
- 
### Additions:
- Added a home menu screen with a background image relevant to the game, buttons to start the game, read the help instructions or exit the game.
- Added a help screen which display the instructions of the game and its controls.
- Added background music, button pressed sound effects, ball impact sound effects and ball lost sound effects.
- Added a score system that calculates the score based on the number of bricks destroyed and the type of bricks destroyed.
- Added a permanent high score leaderboard that shows when the player loses all their balls or beats the game by destroying all the bricks.
- Added an additional brick type (concrete) that is a stronger cement brick to make use of the crack class more.
- Added 6 additional playable levels with both new and existing brick type combinations.
- Added paddle shrink as the player progresses through the levels to increase difficulty.