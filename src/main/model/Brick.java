package main.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public abstract class Brick extends Entity implements Collidable {
	
	private int durability, maxdurability;
	private boolean broken;
	
	public Brick(Point2D position, Dimension2D size, Color bordercolor, Color fillcolor, int maxdurability) {
		setPosition(position);
		setSize(size);
		setBorderColor(bordercolor);
		setFillColor(fillcolor);
		this.maxdurability = maxdurability;
		durability = maxdurability;
	}
	
	public Brick(Point2D position, double width, double height, Color bordercolor, Color fillcolor, int maxdurability) {
		setPosition(position);
		setSize(width, height);
		setBorderColor(bordercolor);
		setFillColor(fillcolor);
		this.maxdurability = maxdurability;
		durability = maxdurability;
	}
	
	public Brick(double posX, double posY, Dimension2D size, Color bordercolor, Color fillcolor, int maxdurability) {
		setPosition(posX, posY);
		setSize(size);
		setBorderColor(bordercolor);
		setFillColor(fillcolor);
		this.maxdurability = maxdurability;
		durability = maxdurability;
	}
	
	public Brick(double posX, double posY, double width, double height, Color bordercolor, Color fillcolor, int maxdurability) {
		setPosition(posX, posY);
		setSize(width, height);
		setBorderColor(bordercolor);
		setFillColor(fillcolor);
		this.maxdurability = maxdurability;
		durability = maxdurability;
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
	
	public void destroy() {
		durability = 0;
		broken = true;
	}
	
	public void repair() {
		durability = maxdurability;
		broken = false;
	}
}