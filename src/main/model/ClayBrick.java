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
 * The {@code ClayBrick} class represents the basic type of brick entity that has a maximum durability of {@code 1} and gives a score of {@code 10} when destroyed.
 * Note: Refactored class hierarchy and structure, removed unused constant and converted to JavaFX.
 * 
 * @author Lim Tze Yang
 */
public class ClayBrick extends Brick {
	
	private static final Color FILL_COLOR = Color.rgb(178, 34, 34).darker();
    private static final Color BORDER_COLOR = Color.GRAY;
    private static final int SCORE = 10;
    private static final int DURABILITY = 1;    
    
    /**
     * Creates a new instance of ClayBrick with the given position and size.
     * @param position The coordinates of the upper-left corner of the brick.
     * @param size The size of the brick.
     */
    public ClayBrick(Point2D position, Dimension2D size) {
    	super(position, size, BORDER_COLOR, FILL_COLOR, SCORE, DURABILITY);
    }
    
    /**
     * Creates a new instance of ClayBrick with the given position and size.
     * @param x The x-coordinate of the upper-left corner of the brick.
     * @param y The y-coordinate of the upper-left corner of the brick.
     * @param size The size of the brick.
     */
    public ClayBrick(double x, double y, Dimension2D size) {
		super(x, y, size, BORDER_COLOR, FILL_COLOR, SCORE, DURABILITY);
	}
    
    /**
     * Creates a new instance of ClayBrick with the given position, width and height.
     * @param position The coordinates of the upper-left corner of the brick.
     * @param width The width of the brick.
     * @param height The height of the brick.
     */
    public ClayBrick(Point2D position, double width, double height) {
		super(position, width, height, BORDER_COLOR, FILL_COLOR, SCORE, DURABILITY);
	}
	
    /**
     * Creates a new instance of ClayBrick with the given position, width and height.
     * @param x The x-coordinate of the upper-left corner of the brick.
     * @param y The y-coordinate of the upper-left corner of the brick.
     * @param width The width of the brick.
     * @param height The height of the brick.
     */
	public ClayBrick(double x, double y, double width, double height) {
		super(x, y, width, height, BORDER_COLOR, FILL_COLOR, SCORE, DURABILITY);
	}
}