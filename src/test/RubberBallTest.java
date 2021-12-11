package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import main.model.Paddle;
import main.model.RubberBall;

class RubberBallTest {

	RubberBall ball = new RubberBall();
	
	@Test
	final void testMoveTo() {
		Point2D testPosition = new Point2D(600/2, 435/2);
		
		ball.moveTo(testPosition);
		assertEquals(ball.getPosition(), testPosition);
	}
	
	@Test
	final void testInverseHorizontalVelocity() {
		ball.setVelocity(new Point2D(75, 0));
		ball.inverseHorizontalVelocity();
		assertEquals(ball.getVelocity(), new Point2D(-75, 0));
	}
	
	@Test
	final void testInverseVerticalVelocity() {
		ball.setVelocity(new Point2D(0, 75));
		ball.inverseVerticalVelocity();
		assertEquals(ball.getVelocity(), new Point2D(0, -75));
	}
	
	@Test
	final void testHandleBoundaryCollision() {
		BoundingBox bounds = new BoundingBox(0, 0, 600, 435);
		Point2D top = new Point2D(600/2, 0);
		Point2D right = new Point2D(600, 435/2);
		Point2D bottom = new Point2D(600/2, 435);
		Point2D left = new Point2D(0, 435/2);		
		Point2D center = new Point2D(600/2, 435/2);
		
		assertAll(
			() -> {
				ball.moveTo(top);
				assertTrue(ball.handleBoundaryCollision(bounds));
			},
			() -> {
				ball.moveTo(right);
				assertTrue(ball.handleBoundaryCollision(bounds));
			},
			() -> {
				ball.moveTo(left);
				assertTrue(ball.handleBoundaryCollision(bounds));
			},
			() -> {
				ball.moveTo(bottom);
				assertTrue(ball.handleBoundaryCollision(bounds));
			},
			() -> {
				ball.moveTo(center);
				assertFalse(ball.handleBoundaryCollision(bounds));
			}
		);
	}

	@Test
	final void testHandleCollision() {
		Paddle paddle = new Paddle(600/2, 435/2, 60, 90);
		Point2D top = new Point2D(paddle.getPosition().getX() + (paddle.getWidth()/2), paddle.getPosition().getY());
		Point2D outside = new Point2D(0, 0);
		
		assertAll(
			() -> {
				ball.moveTo(top);
				assertTrue(ball.handleCollision(paddle));
			},
			() -> {
				ball.moveTo(outside);
				assertFalse(ball.handleCollision(paddle));
			}
		);
	}
}
