/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2021  Lim Tze Yang
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package main.model;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 * The {@code Ball} class provides definitions of common properties and methods for entities that represent a ball.
 * Note: Refactored class hierarchy and structure and converted to JavaFX.
 * 
 * @author Lim Tze Yang
 */
public abstract class Ball extends Entity implements Collidable, Movable {
		
	private BoundingBox hitBox;
	private double speed = 100;
	private Point2D velocity;
	
	/**
	 * Creates a new instance of Ball with the given position, size, borderColor and fillColor.
	 * @param position The coordinates of the upper-left corner of the ball.
	 * @param size The size of the ball.
	 * @param borderColor The border color of the ball.
	 * @param fillColor The fill color of the ball.
	 */
	public Ball(Point2D position, Dimension2D size, Color borderColor, Color fillColor) {
		super(position, size, borderColor, fillColor);
		hitBox = new BoundingBox(position.getX(), position.getY(), size.getWidth(), size.getHeight());
		velocity = new Point2D(0, 0);
	}
	
	/**
	 * Creates a new instance of Ball with the given position, size, borderColor and fillColor.
	 * @param x The x-coordinate of the upper-left corner of the ball.
	 * @param y The y-coordinate of the upper-left corner of the ball.
	 * @param size The size of the ball.
	 * @param borderColor The border color of the ball.
	 * @param fillColor The fill color of the ball.
	 */
	public Ball(double x, double y, Dimension2D size, Color borderColor, Color fillColor) {
		super(x, y, size, borderColor, fillColor);
		hitBox = new BoundingBox(x, y, size.getWidth(), size.getHeight());
		velocity = new Point2D(0, 0);
	}
	
	/**
	 * Creates a new instance of Ball with the given position, width, height, borderColor and fillColor.
	 * @param position The coordinates of the upper-left corner of the ball.
	 * @param width The width of the ball.
	 * @param height The height of the ball.
	 * @param borderColor The border color of the ball.
	 * @param fillColor The fill color of the ball.
	 */
	public Ball(Point2D position, double width, double height, Color borderColor, Color fillColor) {
		super(position, width, height, borderColor, fillColor);
		hitBox = new BoundingBox(position.getX(), position.getY(), width, height);
		velocity = new Point2D(0, 0);
	}	
	
	/**
	 * Creates a new instance of Ball with the given position, width, height, borderColor and fillColor.
	 * @param x The x-coordinate of the upper-left corner of the ball.
	 * @param y The y-coordinate of the upper-left corner of the ball.
	 * @param width The width of the ball.
	 * @param height The height of the ball.
	 * @param borderColor The border color of the ball.
	 * @param fillColor The fill color of the ball.
	 */
	public Ball(double x, double y, double width, double height, Color borderColor, Color fillColor) {
		super(x, y, width, height, borderColor, fillColor);
		hitBox = new BoundingBox(x, y, width, height);
		velocity = new Point2D(0, 0);
	}
	
	@Override
	public void setSize(Dimension2D size) {
		super.setSize(size);
		updateHitBox();
	}
	
	@Override
	public void setSize(double width, double height) {
		super.setSize(width, height);
		updateHitBox();
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
	public double getSpeed() {
		return speed;
	}

	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	@Override
	public BoundingBox getHitBox() {
		return hitBox;
	}
	
	@Override
	public void updateHitBox() {
		hitBox = new BoundingBox(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
	}
	
	@Override
	public boolean handleBoundaryCollision(Canvas gameCanvas) {
		Bounds boundary = gameCanvas.getBoundsInLocal();
		boolean topBoundary = getHitBox().getMinY() <= boundary.getMinY();    	
    	boolean rightBoundary = getHitBox().getMaxX() >= boundary.getMaxX();
    	boolean bottomBoundary = getHitBox().getMaxY() >= boundary.getMaxY();
    	boolean leftBoundary = getHitBox().getMinX() <= boundary.getMinX();
    	boolean collide = topBoundary || rightBoundary || bottomBoundary || leftBoundary;
    	
    	if (topBoundary) {
    		moveTo(new Point2D(hitBox.getMinX(), boundary.getMinY()));
    		inverseVerticalVelocity();
    	} else if (rightBoundary || leftBoundary) {
    		if (rightBoundary) {
    			moveTo(new Point2D(boundary.getMaxX() - hitBox.getWidth(), hitBox.getMinY()));
    		} else {
    			moveTo(new Point2D(boundary.getMinX() + hitBox.getWidth(), hitBox.getMinY()));
    		} 
    		
    		inverseHorizontalVelocity();
    	}
    	
    	return collide;
	}
	
	@Override
	public boolean handleCollision(Collidable entity) {
		BoundingBox entityHitBox = entity.getHitBox();		
		Point2D down = new Point2D(hitBox.getMinX() + (hitBox.getWidth() / 2), hitBox.getMaxY());
		boolean collide = hitBox.intersects(entityHitBox) && entityHitBox.contains(down);
		
		if (collide) {
			double distance = down.getX() - entityHitBox.getMinX();
			double cosine = ((distance * 2) / entityHitBox.getWidth()) - 1;    		
			
			setVelocity(new Point2D(speed * Math.cos(Math.acos(cosine)), -speed * Math.sin(Math.acos(cosine))));
		}
		
		return collide;
	}
	
	@Override
	public void moveTo(Point2D point) {
		setPosition(point);
		updateHitBox();
	}
	
	@Override
	public void move(double time) {
		setPosition(getPosition().add(velocity.multiply(time)));
		updateHitBox();
	}
	
	@Override
	public void inverseHorizontalVelocity() {
		this.velocity = new Point2D(-velocity.getX(), velocity.getY());
	}
	
	@Override
	public void inverseVerticalVelocity() {
		this.velocity = new Point2D(velocity.getX(), -velocity.getY());
	}	
}