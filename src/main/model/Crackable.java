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

import java.util.ArrayList;
import javafx.geometry.Point2D;

/**
 * The {@code Crackable} interface provides definitions for common methods for bricks that are crackable.
 * Note: This interface was added to apply SOLID principles.
 * 
 * @author Lim Tze Yang
 */
public interface Crackable {
	/**
	 * Constant depth for all cracks.
	 */
	public static final int CRACK_DEPTH = 1;
	/**
	 * Constant steps for all cracks.
	 */
    public static final int CRACK_STEPS = 35;
	
    /**
     * Gets a list containing all the cracks of the brick.
     * @return cracks A list containing all the cracks of the brick.
     */
	public ArrayList<Crack> getCracks();
	
	/**
	 * Add a crack to the brick with the given impact and direction.
	 * @param impact The coordinates of the point of impact with a ball entity.
	 * @param direction The direction in which the crack spreads.
	 */
	public void addCrack(Point2D impact, String direction);
	
	/**
	 * Removes all the cracks of the brick.
	 */
	public void removeCracks();
}