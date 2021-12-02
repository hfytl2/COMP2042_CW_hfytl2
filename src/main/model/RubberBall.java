package main.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class RubberBall extends Ball {
	
	private static final Dimension2D SIZE = new Dimension2D(10, 10);
	private static final Color BORDER_COLOR = Color.TRANSPARENT;
	private static final Color FILL_COLOR = Color.WHITE;    
    
    public RubberBall(Point2D position) {
		super(position, SIZE, BORDER_COLOR, FILL_COLOR);
		updateHitBox();
	}
	
	public RubberBall(double posX, double posY) {
		super(posX, posY, SIZE, BORDER_COLOR, FILL_COLOR);
		updateHitBox();
	}
	
	public RubberBall() {
    	this(0, 0);
    }
}