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
			
	public Game(Canvas gamecanvas, int lives) {
		this.gamecanvas = gamecanvas;
		initializeGame(lives);
	}
	
	public Game(Canvas gamecanvas) {
		this(gamecanvas, 3);
	}
	
	public boolean isStarted() {
		return started;
	}
	
	public void start() {
		started = true;		
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public void pause() {
		paused = true;
	}
	
	public boolean isOver() {
		return gameover;
	}
	
	public void end() {
		started = false;
		paused = false;
		gameover = true;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Paddle getPaddle() {
		return paddle;
	}
	
	public Ball getBall() {
		return ball;
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
	
	public void resetLevel() {		
		level = generateLevels().get(level.getLevel());;
	}
	
	public void nextLevel() {
		int nextlevel = level.getLevel() + 1;
		
		if (nextlevel <= MAX_LEVELS) {
			level = levels.get(nextlevel - 1);
			initializePaddleBall();
		} else {
			 end(); // game over
		}
	}
	
	public void initializePaddleBall() {
		started = false;
		paddle = new Paddle(150, 10);
		Point2D paddlestart = new Point2D((gamecanvas.getWidth() / 2) - (paddle.getWidth() / 2), gamecanvas.getHeight() - paddle.getHeight());
		paddle.moveTo(paddlestart);
		ball = new RubberBall();
		Point2D ballstart = new Point2D((gamecanvas.getWidth() / 2) - (ball.getWidth() / 2), paddlestart.getY() - ball.getHeight());
		ball.moveTo(ballstart);
	}
	
	private void initializeGame(int lives) {
		player = new Player(lives);
		initializePaddleBall();
		levels = generateLevels();
		level = levels.get(0);
	}
	
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