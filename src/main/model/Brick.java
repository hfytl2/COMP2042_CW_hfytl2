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
 * The {@code Brick} class provides definitions of common properties and methods for entities that represent a brick.
 * Note: Refactored class hierarchy and structure, removed unused constant and converted to JavaFX.
 * 
 * @author Lim Tze Yang
 */
public abstract class Brick extends Entity implements Collidable {
	
	private BoundingBox hitbox;
	private int score, durability, maxdurability;
	private boolean destroyed;	
	
	/**
	 * Creates a new instance of Brick with the given position, size, bordercolor, fillcolor, score and maxdurability.
	 * @param position The coordinates of the upper-left corner of the brick.
	 * @param size The size of the brick.
	 * @param bordercolor The border color of the brick.
	 * @param fillcolor The fill color of the brick.
	 * @param score The score of the brick.
	 * @param maxdurability The max durability of the brick.
	 */
	public Brick(Point2D position, Dimension2D size, Color bordercolor, Color fillcolor, int score, int maxdurability) {
		super(position, size, bordercolor, fillcolor);
		hitbox = new BoundingBox(position.getX(), position.getY(), size.getWidth(), size.getHeight());
		this.score = score;
		this.maxdurability = maxdurability;
		durability = maxdurability;
	}
	
	/**
	 * Creates a new instance of Brick with the given position, size, bordercolor, fillcolor, score and maxdurability.
	 * @param x The x-coordinate of the upper-left corner of the brick.
	 * @param y The y-coordinate of the upper-left corner of the brick.
	 * @param size The size of the brick.
	 * @param bordercolor The border color of the brick.
	 * @param fillcolor The fill color of the brick.
	 * @param score The score of the brick.
	 * @param maxdurability The max durability of the brick.
	 */
	public Brick(double x, double y, Dimension2D size, Color bordercolor, Color fillcolor, int score, int maxdurability) {
		super(x, y, size, bordercolor, fillcolor);
		hitbox = new BoundingBox(x, y, size.getWidth(), size.getHeight());
		this.score = score;
		this.maxdurability = maxdurability;
		durability = maxdurability;
	}
	
	/**
	 * Creates a new instance of Brick with the given position, width, height, bordercolor, fillcolor, score and maxdurability.
	 * @param position The coordinates of the upper-left corner of the brick.
	 * @param width The width of the brick.
	 * @param height The height of the brick.
	 * @param bordercolor The border color of the brick.
	 * @param fillcolor The fill color of the brick.
	 * @param score The score of the brick.
	 * @param maxdurability The max durability of the brick.
	 */
	public Brick(Point2D position, double width, double height, Color bordercolor, Color fillcolor, int score, int maxdurability) {
		super(position, width, height, bordercolor, fillcolor);
		hitbox = new BoundingBox(position.getX(), position.getY(), width, height);
		this.score = score;
		this.maxdurability = maxdurability;
		durability = maxdurability;
	}	
	
	/**
	 * Creates a new instance of Brick with the given position, width, height, bordercolor, fillcolor, score and maxdurability.
	 * @param x The x-coordinate of the upper-left corner of the brick.
	 * @param y The y-coordinate of the upper-left corner of the brick.
	 * @param width The width of the brick.
	 * @param height The height of the brick.
	 * @param bordercolor The border color of the brick.
	 * @param fillcolor The fill color of the brick.
	 * @param score The score of the brick.
	 * @param maxdurability The max durability of the brick.
	 */
	public Brick(double x, double y, double width, double height, Color bordercolor, Color fillcolor, int score, int maxdurability) {
		super(x, y, width, height, bordercolor, fillcolor);
		hitbox = new BoundingBox(x, y, width, height);
		this.score = score;
		this.maxdurability = maxdurability;
		durability = maxdurability;
	}
	
	@Override
	public BoundingBox getHitBox() {
		return hitbox;
	}
	
	@Override
	public void updateHitBox() {
		hitbox = new BoundingBox(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
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
	 * Gets the value of the property maxdurability.
	 * @return maxdurability The maximum durability of the brick.
	 */
	public int getMaxDurability() {
		return maxdurability;
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