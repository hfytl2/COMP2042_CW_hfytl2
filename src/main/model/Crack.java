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

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class Crack {
	
	private static final int CRACK_SECTIONS = 3;
	private static final double JUMP_PROBABILITY = 0.7;
	
    private Random rng;
	private Entity parent;
	private Path path;
	private int depth, steps;
	
	public Crack(Entity parent, int depth, int steps, Point2D impact, String direction) {
		this.parent = parent;
		this.depth = depth;
		this.steps = steps;
		generateCrack(impact, direction);
	}
	
	public Path getPath() {
		return path;
	}
	
	public void reset() {
		path.getElements().clear();
	}
	
	private void generateCrack(Point2D point, String direction) {
		BoundingBox bounds = new BoundingBox(parent.getPosition().getX(), parent.getPosition().getY(), parent.getSize().getWidth(), parent.getSize().getHeight());
		Point2D impact = point;
		Point2D start, end, random;
		
		switch (direction) {
			case "Up" -> {
				start = new Point2D(bounds.getMinX(), bounds.getMaxY());
	    		end = new Point2D(bounds.getMaxX(), bounds.getMaxY());
	    		random = getRandomPoint(start, end, "Horizontal");
	    		createCrack(impact, random);
			}
			case "Right" -> {
				start = new Point2D(bounds.getMinX(), bounds.getMinY());
	            end = new Point2D(bounds.getMinX(), bounds.getMaxY());;
	            random = getRandomPoint(start, end, "Vertical");
	            createCrack(impact, random);
			}
			case "Down" -> {
				start = new Point2D(bounds.getMinX(), bounds.getMinY());
	            end = new Point2D(bounds.getMaxX(), bounds.getMinY());;
	            random = getRandomPoint(start, end, "Horizontal");
	            createCrack(impact, random);
			}
			case "Left" -> {
				start = new Point2D(bounds.getMaxX(), bounds.getMinY());
	    		end = new Point2D(bounds.getMaxX(), bounds.getMaxY());
	    		random = getRandomPoint(start, end, "Vertical");
	    		createCrack(impact, random);
			}
		}
	}
	
	private void createCrack(Point2D start, Point2D end) {
    	double x, y;
    	double w = (end.getX() - start.getX()) / (double)steps;
    	double h = (end.getY() - start.getY()) / (double)steps;
    	int bound = depth;
    	int jump = bound * 5;
    	
    	path.getElements().add(new MoveTo(start.getX(), start.getY()));
    	
    	for (int i = 1; i < steps; i++) {
    		x = (i * w) + start.getX();
            y = (i * h) + start.getY() + getRandomInBounds(bound);
            
            if(inMiddle(i, CRACK_SECTIONS, steps))
                y += jumps(jump, JUMP_PROBABILITY);

            path.getElements().add(new LineTo(x, y));
    	}
    	
    }
	
	private Point2D getRandomPoint(Point2D from, Point2D to, String direction) {
    	Point2D point = to;
    	int pos;
    	
    	switch (direction) {
	    	case "Horizontal" -> {
	    		pos = (int)(new Random().nextInt((int)(to.getX() - from.getX())) + from.getX());
	    		point = new Point2D(pos, to.getY());
	    	}
	    	case "Vertical" -> {
	    		pos = (int)(new Random().nextInt((int)(to.getY() - from.getY())) + from.getY());
	    		point = new Point2D(to.getX(), pos);
	    	}
	    	default -> throw new IllegalArgumentException("Unknown Direction: " + direction);	    	
    	}
    	
    	return point;
    }
    
    private int getRandomInBounds(int bound) {
        int n = (bound * 2) + 1;
        return rng.nextInt(n) - bound;
    }
    
    private boolean inMiddle(int i, int steps, int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return  (i > low) && (i < up);
    }
    
    private int jumps(int bound, double probability) {
        if (rng.nextDouble() > probability) {
            return getRandomInBounds(bound);
        }
        
        return  0;
    }
}