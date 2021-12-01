package main.model;

import javafx.geometry.Point2D;

interface Movable {
	public void moveUp(double amount);
	public void moveRight(double amount);
	public void moveDown(double amount);
	public void moveLeft(double amount);
	public void moveTo(Point2D point);
}