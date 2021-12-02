package main.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class ClayBrick extends Brick {
	
	private static final Color FILL_COLOR = Color.rgb(178, 34, 34).darker();
    private static final Color BORDER_COLOR = Color.GRAY;
    private static final int SCORE = 1;
    private static final int DURABILITY = 1;    
    
    public ClayBrick(Point2D position, Dimension2D size) {
    	super(position, size, BORDER_COLOR, FILL_COLOR, SCORE, DURABILITY);
    }
        
    public ClayBrick(double posX, double posY, Dimension2D size) {
		super(posX, posY, size, BORDER_COLOR, FILL_COLOR, SCORE, DURABILITY);
	}
    
    public ClayBrick(Point2D position, double width, double height) {
		super(position, width, height, BORDER_COLOR, FILL_COLOR, SCORE, DURABILITY);
	}
	
	public ClayBrick(double posX, double posY, double width, double height) {
		super(posX, posY, width, height, BORDER_COLOR, FILL_COLOR, SCORE, DURABILITY);
	}
}