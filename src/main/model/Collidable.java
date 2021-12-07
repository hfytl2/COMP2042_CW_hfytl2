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

/**
 * The {@code Collidable} interface provides definitions for common methods for entities that are collidable.
 * Note: Added this interface to apply SOLID principles.
 * 
 * @author Lim Tze Yang
 */
interface Collidable {
	/**
	 * Gets the value of the property hitbox.
	 * @return hitbox The bounding box of the entity.
	 */
	public BoundingBox getHitBox();
	
	/**
	 * Updates the value of the property hitbox based on the position and size of the entity.
	 */
	public void updateHitBox();
}