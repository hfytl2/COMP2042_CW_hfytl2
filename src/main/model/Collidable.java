package main.model;

import javafx.geometry.Bounds;

interface Collidable {
	public Bounds getHitBox();
	public void updateHitBox();
}