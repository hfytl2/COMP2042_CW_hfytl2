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
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

/**
 * The {@code Level} class provides definitions for game level generation methods and methods to retrieve information about the level.
 * Note: Extracted methods from {@code Wall} class from source files to apply SOLID principles by adding them to this new class and converted to JavaFX.
 * 
 * @author Lim Tze Yang
 */
public class Level {
	
	private static final int BRICK_COUNT = 30;
	private static final int BRICK_SIZE_RATIO = 6/2;
	private static final int LINES = 3;
	
	private Canvas gamecanvas;
	private int level;
	private String type;	
	private ArrayList<Brick> bricks;
	
	/**
	 * Creates a new instance of Level with the given gamecanvas, level and bricktype.
	 * @param gamecanvas The canvas that the level is to be rendered on.
	 * @param level The level identifier.
	 * @param bricktype The type of brick in the level.
	 */
	public Level(Canvas gamecanvas, int level, String bricktype) {
		this.gamecanvas = gamecanvas;
		this.level = level;
		type = "SingleType";
		bricks = generateSingleTypeLevel(bricktype);
	}
	
	/**
	 * Creates a new instance of Level with the given gamecanvas, level and the bricktypes.
	 * @param gamecanvas The canvas that the level is to be rendered on.
	 * @param level The level identifier.
	 * @param bricktype1 The first type of brick in the level.
	 * @param bricktype2 The second type of brick in the level.
	 */
	public Level(Canvas gamecanvas, int level, String bricktype1, String bricktype2) {
		this.gamecanvas = gamecanvas;
		this.level = level;
		type = "Chessboard";
		bricks = generateChessboardLevel(bricktype1, bricktype2);
	}
	
	/**
	 * Gets the value of the property level.
	 * @return level The level identifier.
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Gets the value of the property type.
	 * @return type The type of level (SingleType/Chessboard).
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Gets a list containing all the brick entities in the level.
	 * @return bricks A list containing all the brick entities in the level.
	 */
	public ArrayList<Brick> getBricks() {
		return bricks;
	}
	
	/**
	 * Generates a level of type SingleType which contains a single type of brick entities.
	 * @param bricktype The type of brick entities of the level.
	 * @return bricks A list containing all the brick entities in the level.
	 */
	private ArrayList<Brick> generateSingleTypeLevel(String bricktype) {
		int brickcount = BRICK_COUNT;
		brickcount -= BRICK_COUNT % LINES;
		int linebricks = brickcount / LINES;
		double brickwidth = gamecanvas.getWidth() / linebricks;
		double brickheight = brickwidth / BRICK_SIZE_RATIO;
		double x, y;
		brickcount += LINES / 2;
		ArrayList<Brick> bricks = new ArrayList<Brick>();
		Point2D position;
		Dimension2D bricksize = new Dimension2D(brickwidth, brickheight);
		int i;
		
		for (i = 0; i < brickcount; i++) {
			int line = i / linebricks;
			
			if (line == LINES)
				break;
			
			x = (i % linebricks) * brickwidth;
			x = (line % 2 == 0) ? x : (x - (brickwidth / 2));
			y = line * brickheight;
			position = new Point2D(x, y);
			bricks.add(createBrick(bricktype, position, bricksize));			
		}
		
		for (y = brickheight; i < brickcount; i++, y += 2 * brickheight) {
			x = (linebricks * brickwidth) - (brickwidth / 2);
			position = new Point2D(x, y);
			bricks.add(createBrick(bricktype, position, bricksize));
		}
		
		return bricks;
	}
	
	/**
	 * Generates a level of type Chessboard which contains two types of brick entities.
	 * @param bricktype1 The first type of brick entity in the level.
	 * @param bricktype2 The second type of brick entity in the level.
	 * @return bricks A list containing all the brick entities in the level.
	 */
	private ArrayList<Brick> generateChessboardLevel(String bricktype1, String bricktype2) {
		int brickcount = BRICK_COUNT;
		brickcount -= BRICK_COUNT % LINES;
		int linebricks = brickcount / LINES;
		int centerleft = linebricks / 2 - 1;
        int centerright = linebricks / 2 + 1;
		double brickwidth = gamecanvas.getWidth() / linebricks;
		double brickheight = brickwidth / BRICK_SIZE_RATIO;
		double x, y;
		brickcount += LINES / 2;
		ArrayList<Brick> bricks = new ArrayList<Brick>();
		Point2D position;
		Dimension2D bricksize = new Dimension2D(brickwidth, brickheight);
		int i;
		
		for (i = 0; i < brickcount; i++) {
			int line = i / linebricks;
			
			if (line == LINES)
				break;
			
			x = (i % linebricks) * brickwidth;
			x = (line % 2 == 0) ? x : (x - (brickwidth / 2)); // move the bricks half of the bricks' width to the left on even lines
			y = line * brickheight;
			position = new Point2D(x, y);			
			boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && position.getX() > centerleft && position.getY() <= centerright));
            bricks.add(b? createBrick(bricktype1, position, bricksize): createBrick(bricktype2, position, bricksize));            
		}
		
		for (y = brickheight; i < brickcount; i++, y += 2 * brickheight) {
			x = (linebricks * brickwidth) - (brickwidth / 2);
			position = new Point2D(x, y);
			bricks.add(createBrick(bricktype1, position, bricksize));
		}
		
		return bricks;
	}
	
	/**
	 * Creates a brick entity to be added to the level.
	 * @param type The type of brick entity.
	 * @param position The position of the upper-left corner of the brick.
	 * @param size The size of the brick.
	 * @return brick The brick entity to be added to the level.
	 */
	private Brick createBrick(String type, Point2D position, Dimension2D size) {
		Brick brick = null;
		
		switch (type) {
			case "Clay" -> brick = new ClayBrick(position, size);
			case "Cement" -> brick = new CementBrick(position, size);
			case "Steel" -> brick = new SteelBrick(position, size);
			default -> throw new IllegalArgumentException("Unknown brick type: " + type);
		}
		
		return brick;
	}
}