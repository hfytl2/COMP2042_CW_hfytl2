package main.model;

import javafx.geometry.Point2D;

interface Movable {
	public Point2D getVelocity();
	public void setVelocity(Point2D velocity);
	public void moveTo(Point2D point);
	public void move(double time);
}