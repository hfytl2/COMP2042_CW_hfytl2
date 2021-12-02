package main.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.model.Game;
import main.model.Level;
import main.model.Paddle;
import main.model.Player;
import main.model.Ball;
import main.model.Brick;

public class GameFrameController {
	
	private static final double BOTTOM_MARGIN = 10;
	
	private Game game;
	private GraphicsContext gc;
	private AnimationTimer timer;
	private long lastnanotime;
	
	@FXML private URL location;	
    @FXML private ResourceBundle resources;
    @FXML private VBox gameroot;
    @FXML private Text txtbricks;
    @FXML private Text txtballs;
    @FXML private Text txtlevel;
    @FXML private Text txtscore;
    @FXML private Canvas gamecanvas;   
    
    public GameFrameController() {}
    
    @FXML
    private void initialize() {
    	game = new Game(gamecanvas);
    	Player player = game.getPlayer();
    	txtbricks.setText("Bricks: " + game.getCurrentLevel().getBricks().size());
    	txtballs.setText("Balls: " + game.getPlayer().getLives());
    	txtlevel.setText("Level: " + game.getCurrentLevel().getLevel());
    	txtscore.setText("Score: " + game.getPlayer().getScore());
    	gameroot.setOnKeyPressed(key -> {
    		player.addInput(key.getCode());
    	});
    	gameroot.setOnKeyReleased(key -> {
    		player.removeInput(key.getCode());
    	});
    	gc = gamecanvas.getGraphicsContext2D();    	
    	renderLevel();
    	renderBall();
    	renderPaddle();
    	lastnanotime = System.nanoTime();
    	timer = new AnimationTimer() {
    		
			@Override
			public void handle(long currentnanotime) {
				double elapsedtime = (currentnanotime - lastnanotime) / 1000000000.0;				
				lastnanotime = currentnanotime;
				Paddle paddle = game.getPaddle();
				Ball ball = game.getBall();
				Level level = game.getCurrentLevel();
		    	ArrayList<Brick> bricks = level.getBricks();
		    	handleInput();	    	
				ball.move(elapsedtime);
				handleBallBoundaryCollision();
				paddle.move(elapsedtime);
				handlePaddleBoundaryCollision();
				gc.clearRect(0, 0, gamecanvas.getWidth(), gamecanvas.getHeight());
				renderLevel();
				renderBall();
				renderPaddle();
			}    		
    	};    	
    	timer.start();
    }
    
    private void renderLevel() {
    	Level level = game.getCurrentLevel();
    	ArrayList<Brick> bricks = level.getBricks();
    	
    	bricks.forEach(brick -> {
			double posX = brick.getPosition().getX();
    		double posY = brick.getPosition().getY();
    		double width = brick.getWidth();
    		double height = brick.getHeight();
    		gc.setFill(brick.getFillColor());
    		gc.setStroke(brick.getBorderColor());
    		gc.fillRect(posX, posY, width, height);
    		gc.strokeRect(posX, posY, width, height);
    	});
    }
    
    private void renderBall() {
    	Ball ball = game.getBall();
    	double posX = ball.getPosition().getX();
    	double posY = ball.getPosition().getY();
    	double width = ball.getWidth();
    	double height = ball.getHeight();
    	
    	gc.setFill(ball.getFillColor());
    	gc.setStroke(ball.getBorderColor());
    	gc.fillOval(posX, posY-BOTTOM_MARGIN, width, height);
    	gc.strokeOval(posX, posY-BOTTOM_MARGIN, width, height);
    }
    
    private void renderPaddle() {
    	Paddle paddle = game.getPaddle();
    	double posX = paddle.getPosition().getX();
    	double posY = paddle.getPosition().getY();
    	double width = paddle.getWidth();
    	double height = paddle.getHeight();
    	
    	gc.setFill(paddle.getFillColor());
    	gc.setStroke(paddle.getBorderColor());
    	gc.fillRect(posX, posY-BOTTOM_MARGIN, width, height);
    	gc.strokeRect(posX, posY-BOTTOM_MARGIN, width, height);
    }     
    
    private void handleBallBoundaryCollision() {    	
    	Player player = game.getPlayer();
    	Bounds boundary = gamecanvas.getBoundsInLocal();
    	Ball ball = game.getBall();
    	BoundingBox hitbox = ball.getHitBox();
    	boolean topboundary = hitbox.getMinY() <= boundary.getMinY();    	
    	boolean rightboundary = hitbox.getMaxX() >= boundary.getMaxX();
    	boolean bottomboundary = hitbox.getMaxY() >= boundary.getMaxY();
    	boolean leftboundary = hitbox.getMinX() <= boundary.getMinX();
    	
    	if (topboundary) {
    		ball.inverseVelocityY();
    	} else if (rightboundary || leftboundary) {
    		ball.inverseVelocityX();
    	} else if (bottomboundary) {
    		player.loseLife();
    		
    		if (player.getLives() != 0) {
    			game.initializePaddleBall();
    		}
    	}
    }
    
    private void handlePaddleBoundaryCollision() {
    	Bounds boundary = gamecanvas.getBoundsInLocal();
    	Paddle paddle = game.getPaddle();
    	Bounds hitbox = paddle.getHitBox();
    	boolean rightboundary = hitbox.getMaxX() >= boundary.getMaxX();
    	boolean leftboundary = hitbox.getMinX() <= boundary.getMinX();
    	
    	if (rightboundary || leftboundary) {
    		paddle.setVelocity(new Point2D(0, 0));
    	}
    }
    
    private void handleInput() {
    	Player player = game.getPlayer();
    	Paddle paddle = game.getPaddle();
    	Ball ball = game.getBall();
    	ArrayList<KeyCode> input = player.getInput();
    	boolean moveleft = input.contains(KeyCode.A) || input.contains(KeyCode.LEFT);
    	boolean moveright = input.contains(KeyCode.D) || input.contains(KeyCode.RIGHT);
    	
    	if (!game.isPaused() && !game.isOver()) {
	    	if (moveleft) {
	    		if (paddle.getHitBox().getMinX() > gamecanvas.getBoundsInLocal().getMinX()) {
	    			paddle.setVelocity(new Point2D(-Paddle.PADDLE_SPEED, 0));
	    		}
			}
			
			if (moveright) {
				if (paddle.getHitBox().getMaxX() < gamecanvas.getBoundsInLocal().getMaxX()) {
					paddle.setVelocity(new Point2D(Paddle.PADDLE_SPEED, 0));
				}
			}
			
			if ((moveleft && moveright) || !(moveleft || moveright)) {
				paddle.setVelocity(new Point2D(0, 0));
			}
			
			if (!game.isStarted() && !game.isOver()) {
				ball.setVelocity(paddle.getVelocity());
			}
    	}
    }	
}