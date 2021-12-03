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

import javafx.geometry.Point2D;

/**
 * The {@code Movable} interface provides definitions for common methods for entities that update their position dynamically.
 * 
 * @author Lim Tze Yang
 */
interface Movable {
	/**
	 * Gets the value of the property velocity.
	 * @return velocity The velocity of the entity.
	 */
	public Point2D getVelocity();
	
	/**
	 * Sets the value of the property velocity.
	 * @param velocity The new velocity of the entity.
	 */
	public void setVelocity(Point2D velocity);
	
	/**
	 * Moves the entity to the given coordinates.
	 * @param point The coordinates to move the entity to.
	 */
	public void moveTo(Point2D point);
	
	/**
	 * Updates the position of the entity based on the velocity and the given elapsed time.
	 * @param time The elapsed time since the position of the entity was last updated.
	 */
	public void move(double time);
	
	/**
	 * Inverses the horizontal velocity of the entity.
	 */
	public void inverseHorizontalVelocity();
}