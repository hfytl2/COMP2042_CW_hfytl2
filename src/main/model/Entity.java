package main.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public abstract class Entity {
	
	private Point2D center, position;
	private Color bordercolor, fillcolor;
	private Dimension2D radii, size;
	
	public Point2D getCenter() {
		return center;
	}
	
	public void setCenter(Point2D point) {
		center = point;
	}
	
	public void setCenter(double centerX, double centerY) {
		center = new Point2D(centerX, centerY);
	}
	
	public Dimension2D getRadii() {
		return radii;
	}
	
	public void setRadii(Dimension2D radii) {
		this.radii = radii;
	}
	
	public void setRadii(double radiusX, double radiusY) {
		this.radii = new Dimension2D(radiusX, radiusY);
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
}