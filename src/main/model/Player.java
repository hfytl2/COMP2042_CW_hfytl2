package main.model;

import java.util.ArrayList;

public class Player {
	
	private String name;
	private int lives, score;
	private ArrayList<String> input;
	
	public Player() {
		this.name = "Player";
		this.lives = 3;
		this.score = 0;
	}
	
	public Player(String name) {
		this.name = name;
		this.lives = 3;
		this.score = 0;
	}
	
	public Player(int lives) {
		this.name = "Player";
		this.lives = lives;
		this.score = 0;
	}
	
	public Player(String name, int lives) {
		this.name = name;
		this.lives = lives;
		this.score = 0;
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
	
	public void decreaseLives(int decrement) {
		lives -= decrement;
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
	
	public ArrayList<String> getInput() {
		return input;
	}
	
	public void addInput(String code) {
		if (!input.contains(code)) {
			input.add(code);
		}
	}
	
	public void removeInput(String code) {
		if (input.contains(code)) {
			input.remove(code);
		}
	}
}