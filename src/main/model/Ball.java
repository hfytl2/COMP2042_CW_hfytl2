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

public abstract class Ball extends Entity implements Collidable, Movable {
	
	private BoundingBox hitbox;
	private Point2D velocity;
	
	public Ball(Point2D position, Dimension2D size, Color bordercolor, Color fillcolor) {
		super(position, size, bordercolor, fillcolor);
		hitbox = new BoundingBox(position.getX(), position.getY(), size.getWidth(), size.getHeight());
		velocity = new Point2D(0, 0);
	}
	
	public Ball(double posX, double posY, Dimension2D size, Color bordercolor, Color fillcolor) {
		super(posX, posY, size, bordercolor, fillcolor);
		hitbox = new BoundingBox(posX, posY, size.getWidth(), size.getHeight());
		velocity = new Point2D(0, 0);
	}
	
	public Ball(Point2D position, double width, double height, Color bordercolor, Color fillcolor) {
		super(position, width, height, bordercolor, fillcolor);
		hitbox = new BoundingBox(position.getX(), position.getY(), width, height);
		velocity = new Point2D(0, 0);
	}	
	
	public Ball(double posX, double posY, double width, double height, Color bordercolor, Color fillcolor) {
		super(posX, posY, width, height, bordercolor, fillcolor);
		hitbox = new BoundingBox(posX, posY, width, height);
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
	
	@Override 
	public BoundingBox getHitBox() {
		return hitbox;
	}
	
	@Override 
	public void updateHitBox() {
		hitbox = new BoundingBox(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
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
	
	public void inverseVelocityX() {
		this.velocity = new Point2D(-velocity.getX(), velocity.getY());
	}
	
	public void inverseVelocityY() {
		this.velocity = new Point2D(velocity.getX(), -velocity.getY());
	}
}