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

public class Level {
	
	private static final int BRICK_COUNT = 30;
	private static final int BRICK_SIZE_RATIO = 6/2;
	private static final int LINES = 3;
	
	private Canvas gamecanvas;
	private int level;
	private String type;	
	private ArrayList<Brick> bricks;
	
	public Level(Canvas gamecanvas, int level, String bricktype) {
		this.gamecanvas = gamecanvas;
		this.level = level;
		type = "SingleType";
		bricks = generateSingleTypeLevel(bricktype);
	}
	
	public Level(Canvas gamecanvas, int level, String bricktype1, String bricktype2) {
		this.gamecanvas = gamecanvas;
		this.level = level;
		type = "Chessboard";
		bricks = generateChessboardLevel(bricktype1, bricktype2);
	}
	
	public int getLevel() {
		return level;
	}
	
	public String getType() {
		return type;
	}
	
	public ArrayList<Brick> getBricks() {
		return bricks;
	}
	
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
			x = (line % 2 == 0) ? x : (x - (brickwidth / 2)); // move the bricks half of the bricks' width to the left on even lines
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