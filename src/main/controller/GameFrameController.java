package main.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.model.Game;
import main.model.Level;
import main.model.Paddle;
import main.model.Ball;
import main.model.Brick;

public class GameFrameController {
	
	private static final double BOTTOM_MARGIN = 10;
	
	private Game game;
	private GraphicsContext gc;
	
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
    	gc = gamecanvas.getGraphicsContext2D();
    	renderLevel();    	
    	renderBall();
    	renderPaddle();
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
    
    private void renderLevel() {
    	Level level = game.getCurrentLevel();
    	ArrayList<Brick> bricks = level.getBricks();
    	bricks.forEach(brick -> {
    		if (!brick.isBroken()) {
    		double posX = brick.getPosition().getX();
        		double posY = brick.getPosition().getY();
        		double width = brick.getWidth();
        		double height = brick.getHeight();
        		gc.setFill(brick.getFillColor());
        		gc.setStroke(brick.getBorderColor());
        		gc.fillRect(posX, posY, width, height);
        		gc.strokeRect(posX, posY, width, height);
    		}
    	});
    }
    
    
}