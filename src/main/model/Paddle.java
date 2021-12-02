package main.model;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Paddle extends Entity implements Collidable, Movable {
	
	private static final Color BORDER_COLOR = Color.LIME.darker().darker();
    private static final Color FILL_COLOR = Color.LIME;
    public static final double PADDLE_SPEED = 5;
	
	BoundingBox hitbox;
	Point2D velocity;
		
	public Paddle(Point2D position, Dimension2D size) {
		super(position, size, BORDER_COLOR, FILL_COLOR);
		hitbox = new BoundingBox(position.getX(), position.getY(), size.getWidth(), size.getHeight());
	}
	
	public Paddle(double posX, double posY, Dimension2D size) {
		super(posX, posY, size, BORDER_COLOR, FILL_COLOR);
		hitbox = new BoundingBox(posX, posY, size.getWidth(), size.getHeight());
	}
	
	public Paddle(Point2D position, double width, double height) {
		super(position, width, height, BORDER_COLOR, FILL_COLOR);
		hitbox = new BoundingBox(position.getX(), position.getY(), width, height);
	}	
	
	public Paddle(double posX, double posY, double width, double height) {
		super(posX, posY, width, height, BORDER_COLOR, FILL_COLOR);
		hitbox = new BoundingBox(posX, posY, width, height);
	}
	
	public Paddle(Dimension2D size) {
		this(0, 0, size);
	}
	
	public Paddle(double width, double height) {
		this(0, 0, width, height);
	}
	
	@Override
	public void setSize(Dimension2D size) {
		setSize(size);
		updateHitBox();
	}
	
	@Override
	public void setSize(double width, double height) {
		setSize(width, height);
		updateHitBox();
	}
	
	@Override
	public Bounds getHitBox() {
		return hitbox;
	}

	@Override 
	public void updateHitBox() {
		hitbox = new BoundingBox(getPosition().getX(), getPosition().getY(), getSize().getWidth(), getSize().getHeight());
	}
	
	@Override
	public Point2D getVelocity() {
		return velocity;
	}

	@Override
	public void setVelocity(Point2D velocity) {
		this.velocity = velocity;
	}

	@Override
	public void moveTo(Point2D point) {
		setPosition(point);
		updateHitBox();
	}

	@Override
	public void move() {
		setPosition(getPosition().add(velocity));
		updateHitBox();
	}	
}