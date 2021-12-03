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
import javafx.scene.input.KeyCode;

public class Player {
	
	private String name;
	private int lives, score;
	private ArrayList<KeyCode> input;	
	
	public Player(String name, int lives) {
		this.name = name;
		this.lives = lives;
		this.score = 0;
		input = new ArrayList<KeyCode>();
	}
	
	public Player(String name) {
		this(name, 3);
	}
	
	public Player(int lives) {
		this("Player", lives);
	}	
	
	public Player() {
		this("Player", 3);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getLives() {
		return lives;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public void loseLife() {
		lives -= 1;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void increaseScore(int increment) {
		score += increment;
	}
	
	public ArrayList<KeyCode> getInput() {
		return input;
	}
	
	public void addInput(KeyCode code) {
		if (!input.contains(code)) {
			input.add(code);
		}
	}
	
	public void removeInput(KeyCode code) {
		if (input.contains(code)) {
			input.remove(code);
		}
	}
}