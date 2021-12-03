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

public abstract class Entity {
	
	private Point2D position;
	private Color bordercolor, fillcolor;
	private Dimension2D size;
	
	public Entity(Point2D position, Dimension2D size, Color bordercolor, Color fillcolor) {
		this.position = position;
		this.size = size;
		this.bordercolor = bordercolor;
		this.fillcolor = fillcolor;
	}
	
	public Entity(double posX, double posY, Dimension2D size, Color bordercolor, Color fillcolor) {
		this(new Point2D(posX, posY), size, bordercolor, fillcolor);
	}
	
	public Entity(Point2D position, double width, double height, Color bordercolor, Color fillcolor) {
		this(position, new Dimension2D(width, height), bordercolor, fillcolor);
	}
	
	public Entity(double posX, double posY, double width, double height, Color bordercolor, Color fillcolor) {
		this(new Point2D(posX, posY), new Dimension2D(width, height), bordercolor, fillcolor);
	}
	
	public Point2D getPosition() {
		return position;
	}
	
	public void setPosition(Point2D point) {
		position = point;
	}
	
	public void setPosition(double posX, double posY) {
		position = new Point2D(posX, posY);
	}
	
	public Color getBorderColor() {
		return bordercolor;
	}
	
	public void setBorderColor(Color color) {
		bordercolor = color;
	}
	
	public Color getFillColor() {
		return fillcolor;
	}
	
	public void setFillColor(Color color) {
		fillcolor = color;
	}
	
	public Dimension2D getSize() {
		return size;
	}
	
	public void setSize(Dimension2D size) {
		this.size = size;
	}
	
	public void setSize(double width, double height) {
		this.size = new Dimension2D(width, height);
	}
	
	public double getWidth() {
		return size.getWidth();
	}
	
	public double getHeight() {
		return size.getHeight();
	}
}