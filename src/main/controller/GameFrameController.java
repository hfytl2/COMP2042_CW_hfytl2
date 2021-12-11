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

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.model.Ball;
import main.model.Brick;
import main.model.Crack;
import main.model.Crackable;
import main.model.DebugConsole;
import main.model.Game;
import main.model.Level;
import main.model.Paddle;
import main.model.Player;

/**
 * A controller that handles events in the GameFrame.
 * Note: Added this controller to adhere to MVC pattern and apply SOLID principles.
 * 
 * @author Lim Tze Yang
 */
public class GameFrameController {
	
	private Game game;
	private GraphicsContext graphicsContext;
	private AnimationTimer timer;
	private long lastNanoTime;
	private AudioClip ballBounceSFX = null;
	private AudioClip loseBallSFX = null;
	
	@FXML private URL location;	
    @FXML private ResourceBundle resources;
    @FXML private StackPane gameRoot;
    @FXML private Text txtBricks;
    @FXML private Text txtBalls;
    @FXML private Text txtLevel;
    @FXML private Text txtScore;
    @FXML private Canvas gameCanvas;   
    
    /**
     * Creates a new instance of GameFrameController.
     */
    public GameFrameController() {}
    
    @FXML
    private void initialize() throws URISyntaxException {
    	ballBounceSFX = new AudioClip(getClass().getClassLoader().getResource("main/assets/ballbounce.mp3").toURI().toString());
    	loseBallSFX = new AudioClip(getClass().getClassLoader().getResource("main/assets/loseball.mp3").toURI().toString());
    	game = Game.getGame(gameCanvas);
    	Player player = game.getPlayer();
    	updateGameInfo();
    	gameRoot.focusedProperty().addListener((observableVal, oldVal, newVal) -> {
    		if (!game.isPaused()) {
    			if (!newVal) {
    				if (game.hasStarted()) {
    					pauseGame();
    				}
    			}
    		}
    	});
    	gameRoot.setOnKeyPressed(key -> {
    		switch (key.getCode()) {
    			case ESCAPE -> {
    				if (game.hasStarted() && !game.isOver()) {
    					if (game.isPaused()) {
    						resumeGame();
    					} else {
    						pauseGame();
    					}
    				}
    			}
    			case SPACE -> {
    				if (!game.hasStarted() && !game.isOver() && !game.isPaused()) {
    					startGame();
    				}
    			}
    			case F1 -> {
    				if (key.isAltDown() && key.isShiftDown()) {    					
    					DebugConsole debugconsole = new DebugConsole((Stage)gameRoot.getScene().getWindow());
    					debugconsole.show();
    				}
    			}
    			default -> {
    				player.addInput(key.getCode());
    			}
    		}
    	});
    	gameRoot.setOnKeyReleased(key -> {
    		player.removeInput(key.getCode());
    	});
    	gameRoot.getChildren().addListener((ListChangeListener<Node>) change -> {
    		while (change.next()) {
    			if (change.wasRemoved()) {
    				if (change.getRemoved().get(0).getId().equals("pauseRoot")) {
    					if (change.getRemoved().get(0).getUserData() != null) {
    						restartGame();
    					}else {
    						resumeGame();	
    					}
    				}
    			}
    		}
    	});    	
    	graphicsContext = gameCanvas.getGraphicsContext2D();    	
    	renderLevel();
    	renderBall();
    	renderPaddle();
    	lastNanoTime = System.nanoTime();
    	timer = new AnimationTimer() {
    		
			@Override
			public void handle(long currentnanotime) {
				double elapsedtime = (currentnanotime - lastNanoTime) / 1000000000.0;				
				lastNanoTime = currentnanotime;
				Paddle paddle = game.getPaddle();
				Ball ball = game.getBall();				
				if (!game.isPaused() && !game.isOver()) {
					updateGameInfo();
					handlePaddleMovement();
					ball.move(elapsedtime);
					paddle.move(elapsedtime);
					
					if (ball.handleBoundaryCollision(gameCanvas.getBoundsInLocal())) {
				    	if (ball.getHitBox().getMaxY() >= gameCanvas.getBoundsInLocal().getMaxY()) {
				    		loseBallSFX.play();
				    		player.loseLife();
				    		
				    		if (player.getLives() != 0) {
				    			game.resetPaddleBallPosition();
				    		} else {
				    			gameOver();
				    		}
				    	} else {
				    		ballBounceSFX.play();
				    	}
					}
					
					handleBrickCollision();
			    	if (ball.handleCollision(paddle) && game.hasStarted()) ballBounceSFX.play();
					paddle.handleBoundaryCollision(gameCanvas.getBoundsInLocal());
					graphicsContext.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
					renderLevel();
					renderBall();
					renderPaddle();
				}		    	
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
			double x = brick.getPosition().getX();
    		double y = brick.getPosition().getY();
    		double width = brick.getWidth();
    		double height = brick.getHeight();
    		graphicsContext.setLineWidth(2);
    		graphicsContext.setFill(brick.getFillColor());
    		graphicsContext.setStroke(brick.getBorderColor());
    		graphicsContext.fillRect(x, y, width, height);
    		graphicsContext.strokeRect(x, y, width, height);
    		
    		if (brick instanceof Crackable) {
    			ArrayList<Crack> cracks = ((Crackable) brick).getCracks();
    			if (cracks != null && !cracks.isEmpty()) {
    				cracks.forEach(crack -> {		
        				Path path = crack.getPath();
        				graphicsContext.setLineWidth(1);
        				graphicsContext.setStroke(brick.getBorderColor());
        				graphicsContext.beginPath();
        				path.getElements().forEach(element -> {
        					if (element instanceof MoveTo) {
        						graphicsContext.moveTo(((MoveTo) element).getX(), ((MoveTo) element).getY());
        					} else if (element instanceof LineTo) {
        						graphicsContext.lineTo(((LineTo) element).getX(), ((LineTo) element).getY());
        						graphicsContext.stroke();
        					}
        				});        				
        				graphicsContext.closePath();
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
    	double x = ball.getPosition().getX();
    	double y = ball.getPosition().getY();
    	double width = ball.getWidth();
    	double height = ball.getHeight();
    	
    	if (!game.isOver()) {
    		graphicsContext.setLineWidth(2);
    		graphicsContext.setFill(ball.getFillColor());
    		graphicsContext.setStroke(ball.getBorderColor());
    		graphicsContext.fillOval(x, y, width, height);
    		graphicsContext.strokeOval(x, y, width, height);
    	}
    }
    
    /**
     * Renders the paddle onto the game canvas.
     */
    private void renderPaddle() {
    	Paddle paddle = game.getPaddle();
    	double x = paddle.getPosition().getX();
    	double y = paddle.getPosition().getY();
    	double width = paddle.getWidth();
    	double height = paddle.getHeight();
    	
    	graphicsContext.setLineWidth(2);
    	graphicsContext.setFill(paddle.getFillColor());
    	graphicsContext.setStroke(paddle.getBorderColor());
    	graphicsContext.fillRect(x, y, width, height);
    	graphicsContext.strokeRect(x, y, width, height);
    }
    
    /**
     * Checks for and handles the collision of the ball with bricks.
     */
    private void handleBrickCollision() {
    	Ball ball = game.getBall();
		ArrayList<Brick> bricks = game.getCurrentLevel().getBricks();
    	Iterator<Brick> brickIterator = bricks.iterator();
    	
    	while (brickIterator.hasNext()) {
    		Brick brick = brickIterator.next();
    		
    		if (brick.handleCollision(ball)) {
    			ballBounceSFX.play();
    				
				if (brick.isDestroyed()) {
					game.getPlayer().increaseScore(brick.getScore());
					brickIterator.remove();
				}  			    			
    		}
    		
    		if (bricks.size() == 0) {
    			if (game.getCurrentLevel().getLevel() < game.getMaxLevels()) {
    				game.nextLevel();
    			} else {
    				gameOver();
    			}
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
    	boolean moveLeft = input.contains(KeyCode.A) || input.contains(KeyCode.LEFT);
    	boolean moveRight = input.contains(KeyCode.D) || input.contains(KeyCode.RIGHT);
    	
    	if (!game.isOver()) {
			if (moveLeft) {
	    		if (paddle.getHitBox().getMinX() > gameCanvas.getBoundsInLocal().getMinX()) {
	    			paddle.setVelocity(new Point2D(-paddle.getSpeed(), 0));
	    		}
			}
			
			if (moveRight) {
				if (paddle.getHitBox().getMaxX() < gameCanvas.getBoundsInLocal().getMaxX()) {
					paddle.setVelocity(new Point2D(paddle.getSpeed(), 0));
				}
			}
			
			if ((moveLeft && moveRight) || !(moveLeft || moveRight)) {
				paddle.setVelocity(new Point2D(0, 0));
			}
			
			if (!game.hasStarted()) {
				ball.setVelocity(paddle.getVelocity());
			}
    	}						
    }
    
    /**
     * Updates game information text.
     */
    private void updateGameInfo() {
    	txtBricks.setText("Bricks: " + game.getCurrentLevel().getBricks().size());
    	txtBalls.setText("Balls: " + game.getPlayer().getLives());
    	txtLevel.setText("Level: " + game.getCurrentLevel().getLevel());
    	txtScore.setText("Score: " + game.getPlayer().getScore());
    }
    
    /**
     * Starts the game.
     */
    private void startGame() {
    	game.start();
    	game.getBall().setVelocity(new Point2D(game.getBall().getSpeed() * Math.cos(Math.toRadians(90)), game.getBall().getSpeed() * Math.sin(Math.toRadians(90))));
    }
    
    /**
     * Pauses the game.
     */
    private void pauseGame() {
    	Parent pauseRoot = null;
    	
    	try {
    		pauseRoot = FXMLLoader.load(getClass().getClassLoader().getResource("main/view/fxml/PauseMenu.fxml"));
    	} catch(Exception e) {
    		e.printStackTrace();
    	}        	
    	gameRoot.getChildren().add(pauseRoot);
    	game.pause();
    	pauseRoot.lookup("#resume").requestFocus();
    }
    
    /**
     * Resumes the game.
     */
    private void resumeGame() {
    	GridPane pauseRoot = (GridPane)gameRoot.lookup("#pauseRoot");
    	
    	if (gameRoot.getChildren().contains(pauseRoot)) {
    		gameRoot.getChildren().remove(pauseRoot);
    	}
    	
		game.resume();
		gameRoot.requestFocus();
    }
    
    /**
     * Restarts the game.
     */
    private void restartGame() {
    	game.restartLevel();
		gameRoot.requestFocus();
    }
    
    /**
     * Shows a frame for the player to input their name to be used for the high score listing.
     */
    private void showGameOverMenu() {
    	Parent root = null;
    	try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("main/view/fxml/GameOverMenu.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	((StackPane)gameRoot.getParent()).getChildren().add(root);
    	root.lookup("#inputField").requestFocus();
    	((TextField)root.lookup("#inputField")).selectAll();
    	((StackPane)gameRoot.getParent()).getChildren().remove(gameRoot);    	
    }
    
    /**
     * Prompts the player to enter their name and shows the game over screen.
     */
    private void gameOver() {
    	game.getPlayer().setScore(game.getPlayer().getScore() + game.getPlayer().getLives() * 100);
    	game.end();
    	timer.stop();
    	showGameOverMenu();
    }
}