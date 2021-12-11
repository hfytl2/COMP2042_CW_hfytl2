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
import javafx.scene.paint.Color;

/**
 * The {@code Paddle} class represents a paddle entity that can collide with and change the direction of a ball entity.
 * Note: Extracted methods from {@code Player} class from source files to distinguish between the in-game player controlled entity and the player itself and converted to JavaFX.
 * 
 * @author Lim Tze Yang
 */
public class Paddle extends Entity implements Collidable, Movable {
	
	private static final Color BORDER_COLOR = Color.LIME.darker().darker();
    private static final Color FILL_COLOR = Color.LIME;
	
	private BoundingBox hitBox;
	private double speed = 100;
	private Point2D velocity;
	
	/**
	 * Creates a new instance of Paddle with the given position and size.
	 * @param position The coordinates of the upper-left corner of the paddle.
	 * @param size The size of the paddle.
	 */
	public Paddle(Point2D position, Dimension2D size) {
		super(position, size, BORDER_COLOR, FILL_COLOR);
		hitBox = new BoundingBox(position.getX(), position.getY(), size.getWidth(), size.getHeight());
		velocity = new Point2D(0, 0);
	}
	
	/**
	 * Creates a new instance of Paddle with the given position and size.
	 * @param x The x-coordinate of the upper-left corner of the paddle.
	 * @param y The y-coordinate of the upper-left corner of the paddle.
	 * @param size The size of the paddle.
	 */
	public Paddle(double x, double y, Dimension2D size) {
		super(x, y, size, BORDER_COLOR, FILL_COLOR);
		hitBox = new BoundingBox(x, y, size.getWidth(), size.getHeight());
		velocity = new Point2D(0, 0);
	}
	
	/**
	 * Creates a new instance of Paddle with the given position, width and height.
	 * @param position The coordinates of the upper-left corner of the paddle.
	 * @param width The width of the paddle.
	 * @param height The height of the paddle.
	 */
	public Paddle(Point2D position, double width, double height) {
		super(position, width, height, BORDER_COLOR, FILL_COLOR);
		hitBox = new BoundingBox(position.getX(), position.getY(), width, height);
		velocity = new Point2D(0, 0);
	}	
	
	/**
	 * Creates a new instance of Paddle with the given position, width and height.
	 * @param x The x-coordinate of the upper-left corner of the paddle.
	 * @param y The y-coordinate of the upper-left corner of the paddle.
	 * @param width The width of the paddle.
	 * @param height The height of the paddle.
	 */
	public Paddle(double x, double y, double width, double height) {
		super(x, y, width, height, BORDER_COLOR, FILL_COLOR);
		hitBox = new BoundingBox(x, y, width, height);
		velocity = new Point2D(0, 0);
	}
	
	/**
	 * Creates a new instance of Paddle at position {@code (0,0)} with the given size.
	 * @param size The size of the paddle.
	 */
	public Paddle(Dimension2D size) {
		this(0, 0, size);
	}
	
	/**
	 * Creates a new instance of Paddle at position {@code (0,0)} with the given width and height.
	 * @param width The width of the paddle.
	 * @param height The height of the paddle.
	 */
	public Paddle(double width, double height) {
		this(0, 0, width, height);
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
	
	/**
	 * Decreases the width of the paddle by the given amount.
	 * @param amount The amount by which the width of the paddle is decreased.
	 */
	public void shrink(double amount) {
		setSize(getWidth() - amount, getHeight());
	}
	
	@Override
	public BoundingBox getHitBox() {
		return hitBox;
	}
 
	@Override
	public void updateHitBox() {
		hitBox = new BoundingBox(getPosition().getX(), getPosition().getY(), getSize().getWidth(), getSize().getHeight());
	}
	
	@Override
	public boolean handleBoundaryCollision(Bounds boundary) {
		boolean rightBoundary = hitBox.getMaxX() >= boundary.getMaxX();
    	boolean leftBoundary = hitBox.getMinX() <= boundary.getMinX();
    	boolean collide = rightBoundary || leftBoundary;
    	
    	if (collide) {
    		setVelocity(new Point2D(0, 0));
    	}
    	
    	return collide;
	}
	
	@Override
	public boolean handleCollision(Collidable entity) {
		return false;
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
	public void move(double time) {
		setPosition(getPosition().add(velocity.multiply(time)));
		updateHitBox();
	}
	
	@Override
	public void inverseHorizontalVelocity() {
		velocity = new Point2D(-velocity.getX(), velocity.getY());
	}

	@Override
	public void inverseVerticalVelocity() {}
}