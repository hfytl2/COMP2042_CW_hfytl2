package main.model;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

public class Game {
	
	private static final int MAX_LEVELS = 4;
	
	private boolean started, paused, gameover;
	private Canvas gamecanvas;
	private Player player;
	private Ball ball;
	private Paddle paddle;
	private ArrayList<Level> levels;
	private Level level;
	
	public Game(Canvas gamecanvas) {
		this.gamecanvas = gamecanvas;
		initializeGame();
	}
	
	public Game(Canvas gamecanvas, int lives) {
		this.gamecanvas = gamecanvas;
		initializeGame(lives);
	}
	
	public boolean isGameStarted() {
		return started;
	}
	
	public void startGame() {
		started = true;
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public void pauseGame() {
		paused = true;
	}
	
	public boolean isGameOver() {
		return gameover;
	}
	
	public void endGame() {
		started = false;
		paused = false;
		gameover = true;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Level getCurrentLevel() {
		return level;
	}
	
	public void setCurrentLevel(int level) {
		if (level <= MAX_LEVELS) {
			this.level = levels.get(level);
		} else {
			this.level = levels.get(MAX_LEVELS);
		}
	}
	
	private void initializeGame() {
		player = new Player();
		paddle = new Paddle(150, 10);
		Point2D paddlestart = new Point2D((gamecanvas.getWidth() / 2) - (paddle.getWidth() / 2), gamecanvas.getHeight() - paddle.getHeight());
		paddle.moveTo(paddlestart);
		ball = new RubberBall();
		Point2D ballstart = new Point2D((gamecanvas.getWidth() / 2) - (ball.getWidth() / 2), paddlestart.getY() - ball.getHeight());
		ball.moveTo(ballstart);
		generateLevels();
		started = paused = gameover = false;
	}
	
	private void initializeGame(int lives) {
		player = new Player(lives);
		paddle = new Paddle(150, 10);
		Point2D paddlestart = new Point2D((gamecanvas.getWidth() / 2) - (paddle.getWidth() / 2), gamecanvas.getHeight() - paddle.getHeight());
		paddle.moveTo(paddlestart);
		ball = new RubberBall();
		Point2D ballstart = new Point2D((gamecanvas.getWidth() / 2) - (ball.getWidth() / 2), paddlestart.getY() - ball.getHeight());
		ball.moveTo(ballstart);
		generateLevels();
	}
	
	private void generateLevels() {
		Level level1 = new Level(gamecanvas, 1, "Clay");
		Level level2 = new Level(gamecanvas, 2, "Clay", "Cement");
		Level level3 = new Level(gamecanvas, 3, "Clay", "Steel");
		Level level4 = new Level(gamecanvas, 4, "Steel", "Cement");
		
		levels.add(level1);
		levels.add(level2);
		levels.add(level3);
		levels.add(level4);
	}
}