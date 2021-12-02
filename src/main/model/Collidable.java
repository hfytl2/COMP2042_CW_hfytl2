package main.model;

import javafx.geometry.BoundingBox;

interface Collidable {
	public BoundingBox getHitBox();
	public void updateHitBox();
}