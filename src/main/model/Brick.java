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