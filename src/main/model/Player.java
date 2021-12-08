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

/**
 * The {@code Player} single class represents the player.
 * Note: Added this class to apply SOLID principles and to have a better distinction between the player and the game entity under their control. 
 * 
 * @author Lim Tze Yang
 */
public class Player {
	
	/**
	 * The number of lives the player starts with.
	 */
	public static final int MAX_LIVES = 3;
	
	private static Player player = null;
	
	private String name;
	private int lives, score;
	private ArrayList<KeyCode> input;
	
	/**
	 * Creates a new instance of Player.
	 */
	private Player() {
		initializePlayer();
	}
	
	/**
	 * Creates a new singleton instance of the player if it doesn't already exit or return the existing instance of the player.
	 * @return player The singleton instance of the player.
	 */
	public static Player getPlayer() {
		if (player == null) {
			player = new Player();
		}
		
		return player;
	}
	
	/**
	 * Gets the value of the property name.
	 * @return name The name of the player.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the value of the property name.
	 * @param name The new name of the player.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the value of the property lives.
	 * @return lives The lives of the player.
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * Sets the value of the property lives
	 * @param lives The new lives of the player.
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	/**
	 * Sets the value of the property lives to {@value #MAX_LIVES}.
	 */
	public void resetLives() {
		lives = MAX_LIVES;
	}
	
	/**
	 * Decreases the value of the property lives by {@code 1}.
	 */
	public void loseLife() {
		lives -= 1;
	}
	
	/**
	 * Gets the value of the property score.
	 * @return score The score of the player.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Sets the value of the property score.
	 * @param score The new score of the player.
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Increases the value of the property score by the given increment.
	 * @param increment The amount by which the score of the player is increased.
	 */
	public void increaseScore(int increment) {
		score += increment;
	}
	
	/**
	 * Gets the list containing all inputs from the player.
	 * @return input The list of inputs of the player.
	 */
	public ArrayList<KeyCode> getInput() {
		return input;
	}
	
	/**
	 * Adds an input to the list of inputs of the player if it isn't already in the list.
	 * @param code The {@link javafx.scene.input.KeyCode KeyCode} of the input to be added.
	 */
	public void addInput(KeyCode code) {
		if (!input.contains(code)) {
			input.add(code);
		}
	}
	
	/**
	 * Removes an input from the list of inputs of the player if it is in the list.
	 * @param code The {@link javafx.scene.input.KeyCode KeyCode} of the input to be removed.
	 */
	public void removeInput(KeyCode code) {
		if (input.contains(code)) {
			input.remove(code);
		}
	}
	
	/**
	 * Set player state to initial state and return the singleton player instance.
	 * @return player The singleton player instance.
	 */
	public Player initializePlayer() {
		this.name = "Player";
		this.lives = MAX_LIVES;
		this.score = 0;
		input = new ArrayList<KeyCode>();
		return this;
	}
}