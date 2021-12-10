/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2021  Lim Tze Yang
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package main.model;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

/**
 * The {@code Game} class represents the game.
 * Note: Extracted methods from {@code Wall} class from source files to apply SOLID principles by adding them to this new class and converted to JavaFX.
 * 
 * @author Lim Tze Yang
 */
public class Game {
	
	/**
	 * Maximum number of levels for the game.
	 */
	public static final int MAX_LEVELS = 4;
	/**
	 * Number used to offset the paddle and ball from the bottom of the screen.
	 */
	private static final int BOTTOM_OFFSET = 13;
	/**
	 * The number by which the width of the paddle shrinks after each level.
	 */
	private static final double PADDLE_SHRINK = 10;
	/**
	 * The minimum width of the paddle.
	 */
	private static final double MIN_PADDLE_WIDTH = 50;
	
	private static Game game;
	
	private boolean started, paused, gameover;
	private Canvas gameCanvas;
	private Player player;
	private Ball ball;
	private Paddle paddle;
	private ArrayList<Level> levels;
	private Level level;
	
	/**
	 * Creates a new instance of Game with the given gameCanvas.
	 * @param gameCanvas The {@link javafx.scene.canvas.Canvas Canvas} in which the game is to be rendered.
	 */
	private Game(Canvas gameCanvas) {
		this.gameCanvas = gameCanvas;
		initializeGame();
	}
	
	/**
	 * Creates a new singleton instance of the game with the given gameCanvas if it doesn't already exit or return the existing instance of the game.
	 * @param gameCanvas The {@link javafx.scene.canvas.Canvas Canvas} in which the game is to be rendered.
	 * @return game The singleton instance of the game.
	 */
	public static Game getGame(Canvas gameCanvas) {
		if (game == null) {
			game = new Game(gameCanvas);
		}
		
		return game;
	}
	
	/**
	 * Creates a new singleton instance of the game if it doesn't already exit or return the existing instance of the game.
	 * @return game The singleton instance of the game.
	 */
	public static Game getGame() {
		return getGame(game.gameCanvas);
	}
	
	/**
	 * Gets the value of the property started.
	 * @return started Defines whether or not the game has started.
	 */
	public boolean hasStarted() {
		return started;
	}
	
	/**
	 * Starts the game.
	 */
	public void start() {
		started = true;		
	}
	
	/**
	 * Gets the value of the property paused.
	 * @return paused Defines whether or not the game is paused.
	 */
	public boolean isPaused() {
		return paused;
	}
	
	/**
	 * Pauses the game.
	 */
	public void pause() {
		paused = true;
	}
	
	/**
	 * Resumes the game.
	 */
	public void resume() {
		paused = false;
	}
	
	/**
	 * Gets the value of the property gameover.
	 * @return gameover Defines whether or not the game is over.
	 */
	public boolean isOver() {
		return gameover;
	}
	
	/**
	 * Ends the game
	 */
	public void end() {
		started = false;
		paused = false;
		gameover = true;
	}
	
	/**
	 * Gets the value of the property player.
	 * @return player The player of the game.
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Gets the value of the property paddle.
	 * @return paddle The paddle entity of the game.
	 */
	public Paddle getPaddle() {
		return paddle;
	}
	
	/**
	 * Gets the value of the property ball.
	 * @return ball The ball entity of the game.
	 */
	public Ball getBall() {
		return ball;
	}
	
	/**
	 * Gets the value of the property level.
	 * @return level The current level of the game.
	 */
	public Level getCurrentLevel() {
		return level;
	}
	
	/**
	 * Sets the value of the property level.
	 * @param level The new level of the game.
	 */
	public void setCurrentLevel(int level) {
		if (level < MAX_LEVELS) {
			this.level = levels.get(level);
		} else {
			this.level = levels.get(MAX_LEVELS - 1);
		}
	}
	
	/**
	 * Restarts the game and moves the paddle and ball to their initial positions.
	 */
	public void resetPaddleBall() {		
		started = false;
		paddle = new Paddle(150, 10);
		Point2D paddleStart = new Point2D((gameCanvas.getWidth() / 2) - (paddle.getWidth() / 2), gameCanvas.getHeight() - paddle.getHeight() - BOTTOM_OFFSET);
		paddle.moveTo(paddleStart);
		ball = new RubberBall();
		Point2D ballStart = new Point2D((gameCanvas.getWidth() / 2) - (ball.getWidth() / 2), paddleStart.getY() - ball.getHeight());
		ball.moveTo(ballStart);
	}		
	
	/**
	 * Resets the current level.
	 */
	public void restartLevel() {
		started = false;
		paused = false;
		gameover = false;
		level = generateLevels().get(level.getLevel() - 1);
		resetPaddleBall();
		player.resetLives();
	}
	
	/**
	 * Go to the next level.
	 */
	public void nextLevel() {
		setCurrentLevel(level.getLevel());
		
		if (paddle.getWidth() > MIN_PADDLE_WIDTH) {
			paddle.shrink(PADDLE_SHRINK);
		}
		
		resetPaddleBall();		
	}
	
	/**
	 * Restarts the game to allow the player to try again.
	 */
	public void restartGame() {
		started = false;
		paused = false;
		gameover = false;
		initializeGame();
	}
	
	/**
	 * Generates all the levels of the game.
	 * @return levels A list containing all the levels in the game.
	 */
	private ArrayList<Level> generateLevels() {
		levels = new ArrayList<Level>();		
		levels.add(new Level(gameCanvas, 1, "Clay"));
		levels.add(new Level(gameCanvas, 2, "Clay", "Cement"));
		levels.add(new Level(gameCanvas, 3, "Clay", "Steel"));
		levels.add(new Level(gameCanvas, 4, "Cement"));
		levels.add(new Level(gameCanvas, 5, "Cement", "Steel"));
		levels.add(new Level(gameCanvas, 6, "Steel"));
		levels.add(new Level(gameCanvas, 7, "Steel", "Cement"));
		return levels;
	}
	
	/** 
	 * Set game state to initial state.
	 */
	private void initializeGame() {
		player = Player.getPlayer().initializePlayer();
		resetPaddleBall();
		levels = generateLevels();
		level = levels.get(0);
	}
}