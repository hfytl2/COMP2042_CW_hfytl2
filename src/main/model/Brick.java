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
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 * The {@code Brick} class provides definitions of common properties and methods for entities that represent a brick.
 * Note: Refactored class hierarchy and structure, removed unused constant and converted to JavaFX.
 * 
 * @author Lim Tze Yang
 */
public abstract class Brick extends Entity implements Collidable {
	
	private BoundingBox hitBox;
	private int score, durability, maxDurability;
	private boolean destroyed;	
	
	/**
	 * Creates a new instance of Brick with the given position, size, borderColor, fillColor, score and maxDurability.
	 * @param position The coordinates of the upper-left corner of the brick.
	 * @param size The size of the brick.
	 * @param borderColor The border color of the brick.
	 * @param fillColor The fill color of the brick.
	 * @param score The score of the brick.
	 * @param maxDurability The max durability of the brick.
	 */
	public Brick(Point2D position, Dimension2D size, Color borderColor, Color fillColor, int score, int maxDurability) {
		super(position, size, borderColor, fillColor);
		hitBox = new BoundingBox(position.getX(), position.getY(), size.getWidth(), size.getHeight());
		this.score = score;
		this.maxDurability = maxDurability;
		durability = maxDurability;
	}
	
	/**
	 * Creates a new instance of Brick with the given position, size, borderColor, fillColor, score and maxDurability.
	 * @param x The x-coordinate of the upper-left corner of the brick.
	 * @param y The y-coordinate of the upper-left corner of the brick.
	 * @param size The size of the brick.
	 * @param borderColor The border color of the brick.
	 * @param fillColor The fill color of the brick.
	 * @param score The score of the brick.
	 * @param maxDurability The max durability of the brick.
	 */
	public Brick(double x, double y, Dimension2D size, Color borderColor, Color fillColor, int score, int maxDurability) {
		super(x, y, size, borderColor, fillColor);
		hitBox = new BoundingBox(x, y, size.getWidth(), size.getHeight());
		this.score = score;
		this.maxDurability = maxDurability;
		durability = maxDurability;
	}
	
	/**
	 * Creates a new instance of Brick with the given position, width, height, borderColor, fillColor, score and maxDurability.
	 * @param position The coordinates of the upper-left corner of the brick.
	 * @param width The width of the brick.
	 * @param height The height of the brick.
	 * @param borderColor The border color of the brick.
	 * @param fillColor The fill color of the brick.
	 * @param score The score of the brick.
	 * @param maxDurability The max durability of the brick.
	 */
	public Brick(Point2D position, double width, double height, Color borderColor, Color fillColor, int score, int maxDurability) {
		super(position, width, height, borderColor, fillColor);
		hitBox = new BoundingBox(position.getX(), position.getY(), width, height);
		this.score = score;
		this.maxDurability = maxDurability;
		durability = maxDurability;
	}	
	
	/**
	 * Creates a new instance of Brick with the given position, width, height, borderColor, fillColor, score and maxDurability.
	 * @param x The x-coordinate of the upper-left corner of the brick.
	 * @param y The y-coordinate of the upper-left corner of the brick.
	 * @param width The width of the brick.
	 * @param height The height of the brick.
	 * @param borderColor The border color of the brick.
	 * @param fillColor The fill color of the brick.
	 * @param score The score of the brick.
	 * @param maxDurability The max durability of the brick.
	 */
	public Brick(double x, double y, double width, double height, Color borderColor, Color fillColor, int score, int maxDurability) {
		super(x, y, width, height, borderColor, fillColor);
		hitBox = new BoundingBox(x, y, width, height);
		this.score = score;
		this.maxDurability = maxDurability;
		durability = maxDurability;
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
		return false;
	}
	
	@Override
	public boolean handleCollision(Collidable entity) {
		BoundingBox entityHitBox = entity.getHitBox();
		Point2D upperLeft = new Point2D(entityHitBox.getMinX(), entityHitBox.getMinY());
		Point2D upperRight = new Point2D(entityHitBox.getMaxX(), entityHitBox.getMinY());
		Point2D lowerLeft = new Point2D(entityHitBox.getMinX(), entityHitBox.getMaxY());
		Point2D lowerRight = new Point2D(entityHitBox.getMinX(), entityHitBox.getMaxY());
		boolean collide = hitBox.intersects(entityHitBox);
		
		if (collide) {
			if (!(this instanceof SteelBrick)) {
				damage();
			} else {
				if (((SteelBrick) this).isDamaged()) {    			
					damage();
				}
			}
		}
		
		if (hitBox.contains(upperLeft.midpoint(upperRight)) || hitBox.contains(upperLeft) || hitBox.contains(upperRight)) { 
			((Movable)entity).inverseVerticalVelocity();
			
			if (!isDestroyed()) {
				if (this instanceof Crackable) {    						
					((Crackable) this).addCrack(upperLeft.midpoint(upperRight), "Up");
				}
			}
		} else if (hitBox.contains(upperRight.midpoint(lowerRight)) || hitBox.contains(upperRight) || hitBox.contains(lowerRight)) {
			((Movable)entity).inverseHorizontalVelocity();
			
			if (!isDestroyed()) {
				if (this instanceof Crackable) {
					((Crackable) this).addCrack(upperRight.midpoint(lowerRight), "Right");
				}
			}
		} else if (hitBox.contains(lowerLeft.midpoint(lowerRight)) || hitBox.contains(lowerLeft) || hitBox.contains(lowerRight)) {
			((Movable)entity).inverseVerticalVelocity();
			
			if (!isDestroyed()) {
				if (this instanceof Crackable) {
					((Crackable) this).addCrack(lowerLeft.midpoint(lowerRight), "Down");
				}
			}
		} else if (hitBox.contains(upperLeft.midpoint(lowerLeft))  || hitBox.contains(upperLeft) || hitBox.contains(lowerLeft)) {
			((Movable)entity).inverseHorizontalVelocity();
			
			if (!isDestroyed()) {
				if (this instanceof Crackable) {
					((Crackable) this).addCrack(upperLeft.midpoint(lowerLeft), "Left");
				}
			}
		}
	
		return collide;
	}
	
	/**
	 * Gets the value of the property score.
	 * @return score The score of the brick.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Gets the value of the property durability.
	 * @return durability The durability of the brick.
	 */
	public int getDurability() {
		return durability;
	}
	
	/**
	 * Sets the value of the property durability.
	 * @param durability The new durability of the brick.
	 */
	public void setDurability(int durability) {
		this.durability = durability;
	}
	
	/**
	 * Gets the value of the property maxDurability.
	 * @return maxDurability The maximum durability of the brick.
	 */
	public int getMaxDurability() {
		return maxDurability;
	}
	
	/**
	 * Gets the value of the property destroyed.
	 * @return destroyed Defines whether or not the brick is destroyed.
	 */
	public boolean isDestroyed() {
		return destroyed;
	}
	
	/**
	 * Decreases the durability of the brick by the given damage.
	 * @param damage The amount by which the durability of the brick is decreased.
	 */
	public void damage(int damage) {
		durability -= damage;
		
		if (durability == 0) {
			destroyed = true;
		}
	}
	
	/**
	 * Decreases the durability of the brick by {@code 1}.
	 */
	public void damage() {
		damage(1);
	}
}