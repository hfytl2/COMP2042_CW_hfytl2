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
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * The {@code Paddle} class represents a paddle entity that can collide with and change the direction of a ball entity.
 * 
 * @author Lim Tze Yang
 */
public class Paddle extends Entity implements Collidable, Movable {
	
	private static final Color BORDER_COLOR = Color.TRANSPARENT;
    private static final Color FILL_COLOR = Color.WHITE;
    public static final double PADDLE_SPEED = 100;
	
	private BoundingBox hitbox;
	private Point2D velocity;
	
	/**
	 * Creates a new instance of Paddle with the given position and size.
	 * @param position The coordinates of the upper-left corner of the paddle.
	 * @param size The size of the paddle.
	 */
	public Paddle(Point2D position, Dimension2D size) {
		super(position, size, BORDER_COLOR, FILL_COLOR);
		hitbox = new BoundingBox(position.getX(), position.getY(), size.getWidth(), size.getHeight());
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
		hitbox = new BoundingBox(x, y, size.getWidth(), size.getHeight());
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
		hitbox = new BoundingBox(position.getX(), position.getY(), width, height);
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
		hitbox = new BoundingBox(x, y, width, height);
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
		setSize(size);
		updateHitBox();
	}
	
	@Override
	public void setSize(double width, double height) {
		setSize(width, height);
		updateHitBox();
	}
	
	public BoundingBox getHitBox() {
		return hitbox;
	}
 
	public void updateHitBox() {
		hitbox = new BoundingBox(getPosition().getX(), getPosition().getY(), getSize().getWidth(), getSize().getHeight());
	}
	
	public Point2D getVelocity() {
		return velocity;
	}

	public void setVelocity(Point2D velocity) {
		this.velocity = velocity;
	}

	public void moveTo(Point2D point) {
		setPosition(point);
		updateHitBox();
	}

	public void move(double time) {
		setPosition(getPosition().add(velocity.multiply(time)));
		updateHitBox();
	}
	
	public void inverseHorizontalVelocity() {
		velocity = new Point2D(-velocity.getX(), velocity.getY());
	}
}