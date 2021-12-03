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

public class Paddle extends Entity implements Collidable, Movable {
	
	private static final Color BORDER_COLOR = Color.TRANSPARENT;
    private static final Color FILL_COLOR = Color.WHITE;
    public static final double PADDLE_SPEED = 100;
	
	private BoundingBox hitbox;
	private Point2D velocity;
		
	public Paddle(Point2D position, Dimension2D size) {
		super(position, size, BORDER_COLOR, FILL_COLOR);
		hitbox = new BoundingBox(position.getX(), position.getY(), size.getWidth(), size.getHeight());
		velocity = new Point2D(0, 0);
	}
	
	public Paddle(double posX, double posY, Dimension2D size) {
		super(posX, posY, size, BORDER_COLOR, FILL_COLOR);
		hitbox = new BoundingBox(posX, posY, size.getWidth(), size.getHeight());
		velocity = new Point2D(0, 0);
	}
	
	public Paddle(Point2D position, double width, double height) {
		super(position, width, height, BORDER_COLOR, FILL_COLOR);
		hitbox = new BoundingBox(position.getX(), position.getY(), width, height);
		velocity = new Point2D(0, 0);
	}	
	
	public Paddle(double posX, double posY, double width, double height) {
		super(posX, posY, width, height, BORDER_COLOR, FILL_COLOR);
		hitbox = new BoundingBox(posX, posY, width, height);
		velocity = new Point2D(0, 0);
	}
	
	public Paddle(Dimension2D size) {
		this(0, 0, size);
	}
	
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
	
	@Override
	public BoundingBox getHitBox() {
		return hitbox;
	}

	@Override 
	public void updateHitBox() {
		hitbox = new BoundingBox(getPosition().getX(), getPosition().getY(), getSize().getWidth(), getSize().getHeight());
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
}