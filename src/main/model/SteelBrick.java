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

import java.util.Random;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * The {@code SteelBrick} class represents a type of brick entity that has a {@code 40%} chance of not getting damaged on impact and a maximum durability of {@code 1} and gives a score of {@code 30} when destroyed.
 * 
 * @author Lim Tze Yang
 */
public class SteelBrick extends Brick {
	
	private static final Color FILL_COLOR = Color.rgb(203, 203, 201);
    private static final Color BORDER_COLOR = Color.BLACK;
    private static final int SCORE = 30;
    private static final int DURABILITY = 1;
    private static final double PROBABILITY = 0.4;
    
    private Random rng;
    
    /**
     * Creates a new instance of SteelBrick with the given position and size.
     * @param position The coordinates of the upper-left corner of the brick.
     * @param size The size of the brick.
     */
    public SteelBrick(Point2D position, Dimension2D size) {
    	super(position, size, BORDER_COLOR, FILL_COLOR, SCORE, DURABILITY);
    }
    
    /**
     * Creates a new instance of SteelBrick with the given position and size.
     * @param x The x-coordinate of the upper-left corner of the brick.
     * @param y The y-coordinate of the upper-left corner of the brick.
     * @param size The size of the brick.
     */
    public SteelBrick(double x, double y, Dimension2D size) {
		super(x, y, size, BORDER_COLOR, FILL_COLOR, SCORE, DURABILITY);
	}
    
    /**
     * Creates a new instance of SteelBrick with the given position, width and height.
     * @param position The coordinates of the upper-left corner of the brick.
     * @param width The width of the brick.
     * @param height The height of the brick.
     */
    public SteelBrick(Point2D position, double width, double height) {
		super(position, width, height, BORDER_COLOR, FILL_COLOR, SCORE, DURABILITY);
	}	
	
    /**
     * Creates a new instance of SteelBrick with the given position, width and height.
     * @param x The x-coordinate of the upper-left corner of the brick.
     * @param y The y-coordinate of the upper-left corner of the brick.
     * @param width The width of the brick.
     * @param height The height of the brick.
     */
	public SteelBrick(double x, double y, double width, double height) {
		super(x, y, width, height, BORDER_COLOR, FILL_COLOR, SCORE, DURABILITY);
	}
	
	/**
	 * Gets whether or not the impact damaged the brick.
	 * @return damaged Defines whether or not the brick is damaged.
	 */
	public boolean isDamaged() {
		return rng.nextDouble() < PROBABILITY;
	}
}