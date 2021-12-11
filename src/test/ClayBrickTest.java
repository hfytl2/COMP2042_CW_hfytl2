package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import javafx.geometry.Point2D;
import main.model.ClayBrick;
import main.model.RubberBall;

class ClayBrickTest {

	ClayBrick brick = new ClayBrick((600/2)-30, (435/2)-20, 60, 40);
	
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
				assertFalse(brick.isDestroyed());
			},
			() -> {
				ball.moveTo(top);
				assertTrue(brick.handleCollision(ball));
				assertTrue(brick.isDestroyed());
			},
			() -> {
				ball.moveTo(right);
				assertTrue(brick.handleCollision(ball));
				assertTrue(brick.isDestroyed());
			},
			() -> {
				ball.moveTo(bottom);
				assertTrue(brick.handleCollision(ball));
				assertTrue(brick.isDestroyed());
			},
			() -> {
				ball.moveTo(left);
				assertTrue(brick.handleCollision(ball));
				assertTrue(brick.isDestroyed());
			}
		);
	}
}
