package main.model;

import java.util.ArrayList;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class CementBrick extends Brick implements Crackable {
	
	private static final Color FILL_COLOR = Color.rgb(147, 147, 147);
    private static final Color BORDER_COLOR = Color.rgb(217, 199, 175);
    private static final int SCORE = 2;
    private static final int DURABILITY = 2;
    
    private ArrayList<Crack> cracks;
    
    public CementBrick(Point2D position, Dimension2D size) {
    	super(position, size, BORDER_COLOR, FILL_COLOR, SCORE, DURABILITY);
    }
    
    public CementBrick(double posX, double posY, Dimension2D size) {
		super(posX, posY, size, BORDER_COLOR, FILL_COLOR, SCORE, DURABILITY);
	}
    
    public CementBrick(Point2D position, double width, double height) {
		super(position, width, height, BORDER_COLOR, FILL_COLOR, SCORE, DURABILITY);
	}	
	
	public CementBrick(double posX, double posY, double width, double height) {
		super(posX, posY, width, height, BORDER_COLOR, FILL_COLOR, SCORE, DURABILITY);
	}
	
	@Override
	public ArrayList<Crack> getCracks() {
		return cracks;
	}

	@Override
	public void addCrack(Point2D impact, String direction) {
		Crack crack = new Crack(this, CRACK_DEPTH, CRACK_STEPS, impact, direction);
		cracks.add(crack);
	}

	@Override
	public void removeCracks() {
		cracks.clear();
	}
}