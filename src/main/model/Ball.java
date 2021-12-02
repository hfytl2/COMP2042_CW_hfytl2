package main.model;

import javafx.geometry.BoundingBox;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public abstract class Ball extends Entity implements Collidable, Movable {
	
	BoundingBox hitbox;
	Point2D velocity;
	
	public Ball(Point2D position, Dimension2D size, Color bordercolor, Color fillcolor) {
		super(position, size, bordercolor, fillcolor);
		hitbox = new BoundingBox(position.getX(), position.getY(), size.getWidth(), size.getHeight());
	}
	
	public Ball(double posX, double posY, Dimension2D size, Color bordercolor, Color fillcolor) {
		super(posX, posY, size, bordercolor, fillcolor);
		hitbox = new BoundingBox(posX, posY, size.getWidth(), size.getHeight());
	}
	
	public Ball(Point2D position, double width, double height, Color bordercolor, Color fillcolor) {
		super(position, width, height, bordercolor, fillcolor);
		hitbox = new BoundingBox(position.getX(), position.getY(), width, height);
	}	
	
	public Ball(double posX, double posY, double width, double height, Color bordercolor, Color fillcolor) {
		super(posX, posY, width, height, bordercolor, fillcolor);
		hitbox = new BoundingBox(posX, posY, width, height);
	}
	
	@Override 
	public BoundingBox getHitBox() {
		return hitbox;
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
	}
	
	@Override
	public void move() {
		setPosition(getPosition().add(velocity));
	}
}