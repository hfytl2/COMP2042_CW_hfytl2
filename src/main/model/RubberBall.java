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
 * The {@code RubberBall} represents the default type of ball entity that can collide with and destroy bricks.
 * 
 * @author Lim Tze Yang
 */
public class RubberBall extends Ball {
	
	private static final Dimension2D SIZE = new Dimension2D(10, 10);
	private static final Color BORDER_COLOR = Color.TRANSPARENT;
	private static final Color FILL_COLOR = Color.WHITE;    
    
	/**
	 * Creates a new instance of RubberBall with the given position.
	 * @param position The position of the upper-left corner of the ball.
	 */
    public RubberBall(Point2D position) {
		super(position, SIZE, BORDER_COLOR, FILL_COLOR);
		updateHitBox();
	}
	
    /**
     * Creates a new instance of RubberBall with the given position.
     * @param x The x-coordinate of the upper-left corner of the ball.
     * @param y The y-coordinate of the upper-left corner of the ball.
     */
	public RubberBall(double x, double y) {
		super(x, y, SIZE, BORDER_COLOR, FILL_COLOR);
		updateHitBox();
	}
	
	/**
	 * Creates a new instance of RubberBall at the position {@code (0,0)}
	 */
	public RubberBall() {
    	this(0, 0);
    }
}