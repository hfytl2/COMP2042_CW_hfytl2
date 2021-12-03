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

package main.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.Random;
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
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import main.model.Game;
import main.model.Level;
import main.model.Paddle;
import main.model.Player;
import main.model.Ball;
import main.model.Brick;
import main.model.Crack;
import main.model.Crackable;

/**
 * A controller that handles events in the GameFrame.
 * 
 * @author Lim Tze Yang
 */
public class GameFrameController {
	
	private static final double BOTTOM_MARGIN = 10;
	
	private Game game;
//	private Random rng;
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
    	updateGameInfo();    	
//    	rng = new Random();
    	gameroot.setOnKeyPressed(key -> {
    		player.addInput(key.getCode());
    		
    		if (!game.isPaused()) {
    			if (key.getCode().equals(KeyCode.ESCAPE)) {
    				game.pause();
    			}
    		
    			if (key.getCode().equals(KeyCode.SPACE)) {    			
    				if (!game.isStarted()) {    					
//    			        double speedx = rng.nextInt(75) - 20;
//    			        double speedy = -rng.nextInt(50);
    			        
    					game.start();
    					game.getBall().setVelocity(new Point2D(-25, -75));
    				}
    			}
    		}
    	});
    	gameroot.setOnKeyReleased(key -> {
    		player.removeInput(key.getCode());
    	});
    	gc = gamecanvas.getGraphicsContext2D();
    	gc.setLineWidth(2);
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
		    	handlePaddleMovement();		    	
				ball.move(elapsedtime);				
				paddle.move(elapsedtime);
				handleBrickCollision();
		    	handlePaddleCollision();
				handleBallBoundaryCollision();
				handlePaddleBoundaryCollision();
				gc.clearRect(0, 0, gamecanvas.getWidth(), gamecanvas.getHeight());
				renderLevel();
				renderBall();
				renderPaddle();
			}    		
    	};    	
    	timer.start();
    }
    
    /**
     * Renders the current level onto the game canvas.
     */
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
    		
    		if (brick instanceof Crackable) {
    			ArrayList<Crack> cracks = ((Crackable) brick).getCracks();
    			if (!cracks.isEmpty()) {
    				cracks.forEach(crack -> {
        				Path path = crack.getPath();
        				gc.setFill(brick.getBorderColor());
        				gc.beginPath();
        				path.getElements().forEach(element -> {
        					if (element instanceof MoveTo) {
        						gc.moveTo(((MoveTo) element).getX(), ((MoveTo) element).getY());
        					} else if (element instanceof LineTo) {
        						gc.lineTo(((LineTo) element).getX(), ((LineTo) element).getY());
        						gc.fill();
        					}
        				});        				
        				gc.closePath();
        			});
    			}    			
    		}
    	});
    }
    
    /**
     * Renders the ball onto the game canvas.
     */
    private void renderBall() {
    	Ball ball = game.getBall();
    	double posX = ball.getPosition().getX();
    	double posY = ball.getPosition().getY() - BOTTOM_MARGIN;
    	double width = ball.getWidth();
    	double height = ball.getHeight();
    	
    	gc.setFill(ball.getFillColor());
    	gc.setStroke(ball.getBorderColor());
    	gc.fillOval(posX, posY, width, height);
    	gc.strokeOval(posX, posY, width, height);
    }
    
    /**
     * Renders the paddle onto the game canvas.
     */
    private void renderPaddle() {
    	Paddle paddle = game.getPaddle();
    	double posX = paddle.getPosition().getX();
    	double posY = paddle.getPosition().getY() - BOTTOM_MARGIN;
    	double width = paddle.getWidth();
    	double height = paddle.getHeight();
    	
    	gc.setFill(paddle.getFillColor());
    	gc.setStroke(paddle.getBorderColor());
    	gc.fillRect(posX, posY, width, height);
    	gc.strokeRect(posX, posY, width, height);
    }
    
    /**
     * Checks for and handles the collision of the paddle with the game boundary.
     */
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
    
    /**
     * Checks for and handles the collision of the ball with bricks.
     */
    private void handleBrickCollision() {    	
    	Ball ball = game.getBall();
    	BoundingBox ballhitbox = ball.getHitBox();
    	Point2D up = new Point2D(ballhitbox.getMinX() + (ballhitbox.getWidth() / 2), ballhitbox.getMinY() - BOTTOM_MARGIN);
		Point2D right = new Point2D(ballhitbox.getMaxX(), ballhitbox.getMinY() + (ballhitbox.getHeight() / 2) - BOTTOM_MARGIN);
		Point2D down = new Point2D(ballhitbox.getMinX() + (ballhitbox.getWidth() / 2), ballhitbox.getMaxY() - BOTTOM_MARGIN);
		Point2D left = new Point2D(ballhitbox.getMinX(), ballhitbox.getMinY() + (ballhitbox.getHeight() / 2) - BOTTOM_MARGIN);
		ArrayList<Brick> bricks = game.getCurrentLevel().getBricks();
    	Iterator<Brick> brickiterator = bricks.iterator();
    	
    	while (brickiterator.hasNext()) {
    		Brick brick = brickiterator.next();
    		BoundingBox brickhitbox = brick.getHitBox();    		
    		
    		if (brickhitbox.intersects(ballhitbox)) {
    			brick.damage();
    			
    			if (brickhitbox.contains(up)) {
    				ball.inverseVerticalVelocity();
    				
    				if (brick.isDestroyed()) {
    					game.getPlayer().increaseScore(brick.getScore());
    					brickiterator.remove();
    					updateGameInfo();
    				} else {
    					if (brick instanceof Crackable) {
        					((Crackable) brick).addCrack(up, "Up");
        				}
    				}
    			} else if (brickhitbox.contains(right)) {
    				ball.inverseHorizontalVelocity();
    				
    				if (brick.isDestroyed()) {
    					brickiterator.remove();
    					updateGameInfo();
    				} else {
    					if (brick instanceof Crackable) {
        					((Crackable) brick).addCrack(right, "Right");
        				}
    				}
    			} else if (brickhitbox.contains(down)) {
    				ball.inverseVerticalVelocity();
    				
    				if (brick.isDestroyed()) {
    					brickiterator.remove();
    					updateGameInfo();
    				} else {
    					if (brick instanceof Crackable) {
        					((Crackable) brick).addCrack(down, "Down");
        				}
    				}
    			} else if (brickhitbox.contains(left)) {
    				ball.inverseHorizontalVelocity();
    				
    				if (brick.isDestroyed()) {
    					brickiterator.remove();
    					updateGameInfo();
    				} else {
    					if (brick instanceof Crackable) {
        					((Crackable) brick).addCrack(left, "Left");
        				}
    				}
    			}    			    			
    		}
    		
    		if (bricks.size() == 0) {
    			game.nextLevel();
    		}
    	}
    }
    
    /**
     * Checks for and handles the collision of the paddle with the ball.
     */
    private void handlePaddleCollision() {
    	Ball ball = game.getBall();
    	Paddle paddle = game.getPaddle();
    	BoundingBox ballhitbox = ball.getHitBox();
    	BoundingBox paddlehitbox = paddle.getHitBox();
    	Point2D down = new Point2D(ballhitbox.getMinX() + (ballhitbox.getWidth() / 2), ballhitbox.getMaxY());
    	boolean impact = paddlehitbox.intersects(ballhitbox) && paddlehitbox.contains(down);
    	
    	if (impact) {
    		ball.inverseVerticalVelocity();
    	}
    }
    
    /**
     * Checks for and handles the collision of the ball with the game boundary.
     */
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
    		ball.inverseVerticalVelocity();
    	} else if (rightboundary || leftboundary) {
    		ball.inverseHorizontalVelocity();
    	} else if (bottomboundary) {
    		player.loseLife();    		
    		updateGameInfo();
    		
    		if (player.getLives() != 0) {
    			game.initializePaddleBall();
    		} else {
    			game.end();
    		}
    	}
    }
    
    /**
     * Handles paddle movement based on user input.
     */
    private void handlePaddleMovement() {
    	Player player = game.getPlayer();
    	Paddle paddle = game.getPaddle();
    	Ball ball = game.getBall();
    	ArrayList<KeyCode> input = player.getInput();
    	boolean moveleft = input.contains(KeyCode.A) || input.contains(KeyCode.LEFT);
    	boolean moveright = input.contains(KeyCode.D) || input.contains(KeyCode.RIGHT);
    	
    	if (!game.isOver()) {
    		if (!game.isPaused()) {
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
    			
    			if (!game.isStarted()) {
    				ball.setVelocity(paddle.getVelocity());
    			}
    		}
    	}						
    }
    
    /**
     * Updates game information text.
     */
    private void updateGameInfo() {
    	txtbricks.setText("Bricks: " + game.getCurrentLevel().getBricks().size());
    	txtballs.setText("Balls: " + game.getPlayer().getLives());
    	txtlevel.setText("Level: " + game.getCurrentLevel().getLevel());
    	txtscore.setText("Score: " + game.getPlayer().getScore());
    }
}