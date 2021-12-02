package main.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public abstract class Entity {
	
	private Point2D position;
	private Color bordercolor, fillcolor;
	private Dimension2D size;
	
	public Entity(Point2D position, Dimension2D size, Color bordercolor, Color fillcolor) {
		this.position = position;
		this.size = size;
		this.bordercolor = bordercolor;
		this.fillcolor = fillcolor;
	}
	
	public Entity(double posX, double posY, Dimension2D size, Color bordercolor, Color fillcolor) {
		setPosition(posX, posY);
		this.size = size;
		this.bordercolor = bordercolor;
		this.fillcolor = fillcolor;
	}
	
	public Entity(Point2D position, double width, double height, Color bordercolor, Color fillcolor) {
		this.position = position;
		setSize(width, height);
		this.bordercolor = bordercolor;
		this.fillcolor = fillcolor;
	}
	
	public Entity(double posX, double posY, double width, double height, Color bordercolor, Color fillcolor) {
		setPosition(posX, posY);
		setSize(width, height);
		this.bordercolor = bordercolor;
		this.fillcolor = fillcolor;
	}
	
	public Point2D getPosition() {
		return position;
	}
	
	public void setPosition(Point2D point) {
		position = point;
	}
	
	public void setPosition(double posX, double posY) {
		position = new Point2D(posX, posY);
	}
	
	public Color getBorderColor() {
		return bordercolor;
	}
	
	public void setBorderColor(Color color) {
		bordercolor = color;
	}
	
	public Color getFillColor() {
		return fillcolor;
	}
	
	public void setFillColor(Color color) {
		fillcolor = color;
	}
	
	public Dimension2D getSize() {
		return size;
	}
	
	public void setSize(Dimension2D size) {
		this.size = size;
	}
	
	public void setSize(double width, double height) {
		this.size = new Dimension2D(width, height);
	}
	
	public double getWidth() {
		return size.getWidth();
	}
	
	public double getHeight() {
		return size.getHeight();
	}
}