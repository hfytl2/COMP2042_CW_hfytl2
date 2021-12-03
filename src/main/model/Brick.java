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

public abstract class Brick extends Entity implements Collidable {
	
	private BoundingBox hitbox;
	private int score, durability, maxdurability;
	private boolean broken;	
	
	public Brick(Point2D position, Dimension2D size, Color bordercolor, Color fillcolor, int score, int maxdurability) {
		super(position, size, bordercolor, fillcolor);
		hitbox = new BoundingBox(position.getX(), position.getY(), size.getWidth(), size.getHeight());
		this.score = score;
		this.maxdurability = maxdurability;
		durability = maxdurability;
	}
	
	public Brick(double posX, double posY, Dimension2D size, Color bordercolor, Color fillcolor, int score, int maxdurability) {
		super(posX, posY, size, bordercolor, fillcolor);
		hitbox = new BoundingBox(posX, posY, size.getWidth(), size.getHeight());
		this.score = score;
		this.maxdurability = maxdurability;
		durability = maxdurability;
	}
	
	public Brick(Point2D position, double width, double height, Color bordercolor, Color fillcolor, int score, int maxdurability) {
		super(position, width, height, bordercolor, fillcolor);
		hitbox = new BoundingBox(position.getX(), position.getY(), width, height);
		this.score = score;
		this.maxdurability = maxdurability;
		durability = maxdurability;
	}	
	
	public Brick(double posX, double posY, double width, double height, Color bordercolor, Color fillcolor, int score, int maxdurability) {
		super(posX, posY, width, height, bordercolor, fillcolor);
		hitbox = new BoundingBox(posX, posY, width, height);
		this.score = score;
		this.maxdurability = maxdurability;
		durability = maxdurability;
	}
	
	public BoundingBox getHitBox() {
		return hitbox;
	}
	
	public void updateHitBox() {
		hitbox = new BoundingBox(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
	}
	
	public int getScore() {
		return score;
	}
	
	public int getDurability() {
		return durability;
	}
	
	public void setDurability(int durability) {
		this.durability = durability;
	}
	
	public int getMaxDurability() {
		return maxdurability;
	}
	
	public boolean isBroken() {
		return broken;
	}
	
	public void damage(int damage) {
		durability -= damage;
		
		if (durability == 0) {
			broken = true;
		}
	}
	
	public void damage() {
		damage(1);
	}
}