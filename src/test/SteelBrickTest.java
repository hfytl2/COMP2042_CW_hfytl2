package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javafx.geometry.Point2D;
import main.model.RubberBall;
import main.model.SteelBrick;

class SteelBrickTest {
	
	SteelBrick brick = new SteelBrick((600/2)-30, (435/2)-20, 60, 40);

	@Test
	final void testHandleCollision() {
		RubberBall ball = new RubberBall();
		Point2D top = new Point2D(brick.getPosition().getX() + (brick.getWidth()/2), brick.getPosition().getY());
		Point2D right = new Point2D(brick.getPosition().getX() + brick.getWidth(), brick.getPosition().getY() + (brick.getHeight()/2));
		Point2D bottom = new Point2D(brick.getPosition().getX() + (brick.getWidth()/2), brick.getPosition().getY() + brick.getHeight());
		Point2D left = new Point2D(brick.getPosition().getX(), brick.getPosition().getY() + (brick.getHeight()/2));
		
		assertAll(
			() -> {
				assertFalse(brick.handleCollision(ball));
			},
			() -> {
				ball.moveTo(top);
				assertTrue(brick.handleCollision(ball));
			},
			() -> {
				ball.moveTo(right);
				assertTrue(brick.handleCollision(ball));
			},
			() -> {
				ball.moveTo(bottom);
				assertTrue(brick.handleCollision(ball));
			},
			() -> {
				ball.moveTo(left);
				assertTrue(brick.handleCollision(ball));
			}
		);
	}

}
