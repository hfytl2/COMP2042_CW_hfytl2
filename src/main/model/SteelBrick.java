package main.model;

import java.util.Random;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class SteelBrick extends Brick {
	
	private static final Color FILL_COLOR = Color.rgb(203, 203, 201);
    private static final Color BORDER_COLOR = Color.BLACK;
    private static final int DURABILITY = 1;
    private static final double PROBABILITY = 0.4;
    
    private Random rng;
    
    public SteelBrick(Point2D position, Dimension2D size) {
    	super(position, size, BORDER_COLOR, FILL_COLOR, DURABILITY);
    }
    
    public SteelBrick(double posX, double posY, Dimension2D size) {
		super(posX, posY, size, BORDER_COLOR, FILL_COLOR, DURABILITY);
	}
    
    public SteelBrick(Point2D position, double width, double height) {
		super(position, width, height, BORDER_COLOR, FILL_COLOR, DURABILITY);
	}	
	
	public SteelBrick(double posX, double posY, double width, double height) {
		super(posX, posY, width, height, BORDER_COLOR, FILL_COLOR, DURABILITY);
	}
	
	public boolean isImpacted() {
		return rng.nextDouble() < PROBABILITY;
	}
}