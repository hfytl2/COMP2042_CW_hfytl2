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
 * 
 * @author Lim Tze Yang
 */
public class Game {
	
	private static final int MAX_LEVELS = 4;
	
	private boolean started, paused, gameover;
	private Canvas gamecanvas;
	private Player player;
	private Ball ball;
	private Paddle paddle;
	private ArrayList<Level> levels;
	private Level level;
	
	/**
	 * Creates a new instance of Game with the given gamecanvas and lives.
	 * @param gamecanvas The {@link javafx.scene.canvas.Canvas Canvas} in which the game is to be rendered.
	 * @param lives The amount of lives with which the player starts with.
	 */
	public Game(Canvas gamecanvas, int lives) {
		this.gamecanvas = gamecanvas;
		initializeGame(lives);
	}
	
	/**
	 * Creates a new instance of Game with the given gamecanvas and {@code 3} lives.
	 * @param gamecanvas The {@link javafx.scene.canvas.Canvas Canvas} in which the game is to be rendered.
	 */
	public Game(Canvas gamecanvas) {
		this(gamecanvas, 3);
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
		if (level <= MAX_LEVELS) {
			this.level = levels.get(level);
		} else {
			this.level = levels.get(MAX_LEVELS);
		}
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
	}
	
	/**
	 * Go to the next level.
	 */
	public void nextLevel() {
		int nextlevel = level.getLevel();
		
		if (nextlevel < MAX_LEVELS) {
			level = levels.get(nextlevel);
			resetPaddleBall();
		} else {
			 end();
		}
	}
	
	/**
	 * Restarts the game and moves the paddle and ball to their initial positions.
	 */
	public void resetPaddleBall() {
		double bottomoffset = 13;
		
		started = false;
		paddle = new Paddle(150, 10);
		Point2D paddlestart = new Point2D((gamecanvas.getWidth() / 2) - (paddle.getWidth() / 2), gamecanvas.getHeight() - paddle.getHeight() - bottomoffset);
		paddle.moveTo(paddlestart);
		ball = new RubberBall();
		Point2D ballstart = new Point2D((gamecanvas.getWidth() / 2) - (ball.getWidth() / 2), paddlestart.getY() - ball.getHeight());
		ball.moveTo(ballstart);
	}
	
	/** 
	 * Initializes the game with the given lives.
	 * @param lives The number of lives that the player starts with.
	 */
	private void initializeGame(int lives) {
		player = new Player(lives);
		resetPaddleBall();
		levels = generateLevels();
		level = levels.get(0);
	}
	
	/**
	 * Generates all the levels of the game.
	 * @return levels A list containing all the levels in the game.
	 */
	private ArrayList<Level> generateLevels() {
		levels = new ArrayList<Level>();		
		Level level1 = new Level(gamecanvas, 1, "Clay");
		Level level2 = new Level(gamecanvas, 2, "Clay", "Cement");
		Level level3 = new Level(gamecanvas, 3, "Clay", "Steel");
		Level level4 = new Level(gamecanvas, 4, "Steel", "Cement");
		
		levels.add(level1);
		levels.add(level2);
		levels.add(level3);
		levels.add(level4);
		return levels;
	}
}