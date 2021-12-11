package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import javafx.geometry.Point2D;
import main.model.CementBrick;
import main.model.RubberBall;

class CementBrickTest {
	
	CementBrick brick = new CementBrick((600/2)-30, (435/2)-20, 60, 40);
	Point2D top = new Point2D(brick.getPosition().getX() + (brick.getWidth()/2), brick.getPosition().getY());
	Point2D right = new Point2D(brick.getPosition().getX() + brick.getWidth(), brick.getPosition().getY() + (brick.getHeight()/2));
	Point2D bottom = new Point2D(brick.getPosition().getX() + (brick.getWidth()/2), brick.getPosition().getY() + brick.getHeight());
	Point2D left = new Point2D(brick.getPosition().getX(), brick.getPosition().getY() + (brick.getHeight()/2));

	@Test
	final void testHandleCollision() {
		RubberBall ball = new RubberBall();
		
		assertAll(
			() -> {
				assertFalse(brick.handleCollision(ball));
			},
			() -> {
				ball.moveTo(top);
				assertTrue(brick.handleCollision(ball));
				assertEquals(brick.getCracks().size(), 1);
			},
			() -> {
				ball.moveTo(right);
				assertTrue(brick.handleCollision(ball));
				assertEquals(brick.getCracks().size(), 1);
			},
			() -> {
				ball.moveTo(bottom);
				assertTrue(brick.handleCollision(ball));
				assertEquals(brick.getCracks().size(), 1);
			},
			() -> {
				ball.moveTo(left);
				assertTrue(brick.handleCollision(ball));
				assertEquals(brick.getCracks().size(), 1);
			}
		);
	}
}
