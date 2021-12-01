package main.model;

import javafx.geometry.BoundingBox;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public abstract class Ball extends Entity implements Collidable, Movable {
	
	BoundingBox hitbox;
	
	public Ball(Point2D position, Dimension2D size, Color bordercolor, Color fillcolor, int maxdurability) {
		setPosition(position);
		setSize(size);
		setBorderColor(bordercolor);
		setFillColor(fillcolor);
		hitbox = new BoundingBox(position.getX(), position.getY(), size.getWidth(), size.getHeight());
	}
	
	public Ball(Point2D position, double width, double height, Color bordercolor, Color fillcolor, int maxdurability) {
		setPosition(position);
		setSize(width, height);
		setBorderColor(bordercolor);
		setFillColor(fillcolor);
		hitbox = new BoundingBox(position.getX(), position.getY(), width, height);
	}
	
	public Ball(double posX, double posY, Dimension2D size, Color bordercolor, Color fillcolor, int maxdurability) {
		setPosition(posX, posY);
		setSize(size);
		setBorderColor(bordercolor);
		setFillColor(fillcolor);
		hitbox = new BoundingBox(posX, posY, size.getWidth(), size.getHeight());
	}
	
	public Ball(double posX, double posY, double width, double height, Color bordercolor, Color fillcolor, int maxdurability) {
		setPosition(posX, posY);
		setSize(width, height);
		setBorderColor(bordercolor);
		setFillColor(fillcolor);
		hitbox = new BoundingBox(posX, posY, width, height);
	}
	
	@Override
	public void moveTo(Point2D point) {
		setPosition(point);
	}
}