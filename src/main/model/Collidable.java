package main.model;

import javafx.geometry.Bounds;

interface Collidable {
	public Bounds getHitBox();
	public void collideBoundary();
	public void collideEntity(Entity entity);
}