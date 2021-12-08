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

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * Root of the game entity hierarchy. Every entity in the game has {@code Entity} as a superclass. All balls, bricks and paddle inherits its properties and implements its methods.
 * Note: Added this abstract class to apply SOLID principles.
 * 
 * @author Lim Tze Yang
 */
public abstract class Entity {
	
	private Point2D position;
	private Color borderColor, fillColor;
	private Dimension2D size;
	
	/**
	 * Creates a new instance of Entity with the given position, size, borderColor and fillColor.
	 * @param position The coordinates of the upper-left corner of the entity.
	 * @param size The size of the entity.
	 * @param borderColor The border color of the entity.
	 * @param fillColor The fill color of the entity.
	 */
	public Entity(Point2D position, Dimension2D size, Color borderColor, Color fillColor) {
		this.position = position;
		this.size = size;
		this.borderColor = borderColor;
		this.fillColor = fillColor;
	}
	
	/**
	 * Creates a new instance of Entity with the given position, size, borderColor and fillColor.
	 * @param x The x-coordinate of the upper-left corner of the entity.
	 * @param y The y-coordinate of the upper-left corner of the entity.
	 * @param size The size of the entity.
	 * @param borderColor The border color of the entity.
	 * @param fillColor The fill color of the entity.
	 */
	public Entity(double x, double y, Dimension2D size, Color borderColor, Color fillColor) {
		this(new Point2D(x, y), size, borderColor, fillColor);
	}
	
	/**
	 * Creates a new instance of {@code Entity} with the given position, width, height, borderColor and fillColor.
	 * @param position The coordinates of the upper-left corner of the entity.
	 * @param width The width of the entity.
	 * @param height The height of the entity.
	 * @param borderColor The border color of the entity.
	 * @param fillColor The fill color of the entity.
	 */
	public Entity(Point2D position, double width, double height, Color borderColor, Color fillColor) {
		this(position, new Dimension2D(width, height), borderColor, fillColor);
	}
	
	/**
	 * Creates a new instance of {@code Entity} with the given position, width, height, borderColor and fillColor.
	 * @param x The x-coordinate of the upper-left corner of the entity.
	 * @param y The y-coordinate of the upper-left corner of the entity.
	 * @param width The width of the entity.
	 * @param height The height of the entity.
	 * @param borderColor The border color of the entity.
	 * @param fillColor The fill color of the entity.
	 */
	public Entity(double x, double y, double width, double height, Color borderColor, Color fillColor) {
		this(new Point2D(x, y), new Dimension2D(width, height), borderColor, fillColor);
	}
	
	/**
	 * Gets the value of the property position.
	 * @return The coordinates of the upper-left corner of the entity.
	 */
	public Point2D getPosition() {
		return position;
	}
	
	/**
	 * Sets the value of the property position.
	 * @param point The new coordinates of the upper-left corner of the entity.
	 */
	public void setPosition(Point2D point) {
		position = point;
	}
	
	/**
	 * Sets the value of the property position.
	 * @param x The new x-coordinate of the upper-left corner of the entity.
	 * @param y The new y-coordinate of the upper-left corner of the entity.
	 */
	public void setPosition(double x, double y) {
		position = new Point2D(x, y);
	}
	
	/**
	 * Gets the value of the property borderColor.
	 * @return The {@link javafx.scene.paint.Color Color} of the outline of the entity.
	 */
	public Color getBorderColor() {
		return borderColor;
	}
	
	/**
	 * Sets the value of the property borderColor.
	 * @param color The new {@link javafx.scene.paint.Color Color} of the outline of the entity.
	 */
	public void setBorderColor(Color color) {
		borderColor = color;
	}
	
	/**
	 * Gets the value of the property fillColor.
	 * @return The {@link javafx.scene.paint.Color Color} that fills the interior of the entity.
	 */
	public Color getFillColor() {
		return fillColor;
	}
	
	/**
	 * Sets the value of the property fillColor.
	 * @param color The new {@link javafx.scene.paint.Color Color} that fills the interior of the entity.
	 */
	public void setFillColor(Color color) {
		fillColor = color;
	}
	
	/**
	 * Gets the value of the property size.
	 * @return The size of the entity.
	 */
	public Dimension2D getSize() {
		return size;
	}
	
	/**
	 * Sets the value of the property size
	 * @param size The new size of the entity.
	 */
	public void setSize(Dimension2D size) {
		this.size = size;
	}
	
	/**
	 * Sets the value of the property size.
	 * @param width The new width of the entity.
	 * @param height The new height of the entity.
	 */
	public void setSize(double width, double height) {
		this.size = new Dimension2D(width, height);
	}
	
	/**
	 * Gets the width value of the property size.
	 * @return The width of the entity.
	 */
	public double getWidth() {
		return size.getWidth();
	}
	
	/**
	 * Gets the height value of the property size.
	 * @return The height of the entity.
	 */
	public double getHeight() {
		return size.getHeight();
	}
}