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
 * The {@code Ball} class provides definitions of common properties and methods for entities that represent a ball.
 * 
 * @author Lim Tze Yang
 */
public abstract class Ball extends Entity implements Collidable, Movable {
	
	private BoundingBox hitbox;
	private Point2D velocity;
	
	/**
	 * Creates a new instance of Ball with the given position, size, bordercolor and fillcolor.
	 * @param position The coordinates of the upper-left corner of the ball.
	 * @param size The size of the ball.
	 * @param bordercolor The border color of the ball.
	 * @param fillcolor The fill color of the ball.
	 */
	public Ball(Point2D position, Dimension2D size, Color bordercolor, Color fillcolor) {
		super(position, size, bordercolor, fillcolor);
		hitbox = new BoundingBox(position.getX(), position.getY(), size.getWidth(), size.getHeight());
		velocity = new Point2D(0, 0);
	}
	
	/**
	 * Creates a new instance of Ball with the given position, size, bordercolor and fillcolor.
	 * @param x The x-coordinate of the upper-left corner of the ball.
	 * @param y The y-coordinate of the upper-left corner of the ball.
	 * @param size The size of the ball.
	 * @param bordercolor The border color of the ball.
	 * @param fillcolor The fill color of the ball.
	 */
	public Ball(double x, double y, Dimension2D size, Color bordercolor, Color fillcolor) {
		super(x, y, size, bordercolor, fillcolor);
		hitbox = new BoundingBox(x, y, size.getWidth(), size.getHeight());
		velocity = new Point2D(0, 0);
	}
	
	/**
	 * Creates a new instance of Ball with the given position, width, height, bordercolor and fillcolor.
	 * @param position The coordinates of the upper-left corner of the ball.
	 * @param width The width of the ball.
	 * @param height The height of the ball.
	 * @param bordercolor The border color of the ball.
	 * @param fillcolor The fill color of the ball.
	 */
	public Ball(Point2D position, double width, double height, Color bordercolor, Color fillcolor) {
		super(position, width, height, bordercolor, fillcolor);
		hitbox = new BoundingBox(position.getX(), position.getY(), width, height);
		velocity = new Point2D(0, 0);
	}	
	
	/**
	 * Creates a new instance of Ball with the given position, width, height, bordercolor and fillcolor.
	 * @param x The x-coordinate of the upper-left corner of the ball.
	 * @param y The y-coordinate of the upper-left corner of the ball.
	 * @param width The width of the ball.
	 * @param height The height of the ball.
	 * @param bordercolor The border color of the ball.
	 * @param fillcolor The fill color of the ball.
	 */
	public Ball(double x, double y, double width, double height, Color bordercolor, Color fillcolor) {
		super(x, y, width, height, bordercolor, fillcolor);
		hitbox = new BoundingBox(x, y, width, height);
		velocity = new Point2D(0, 0);
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
		hitbox = new BoundingBox(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
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
		this.velocity = new Point2D(velocity.getX(), -velocity.getY());
	}
	
	/**
	 * Inverses the vertical velocity of the ball.
	 */
	public void inverseVerticalVelocity() {
		this.velocity = new Point2D(-velocity.getX(), velocity.getY());
	}	
}