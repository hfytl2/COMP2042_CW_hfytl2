package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import main.model.Paddle;

class PaddleTest {

	Paddle paddle = new Paddle(150, 10);
	
	@Test
	final void testHandleBoundaryCollision() {
		BoundingBox bounds = new BoundingBox(0, 0, 600, 435);
		Point2D right = new Point2D(600, 435/2);
		Point2D left = new Point2D(0, 435/2);
		Point2D center = new Point2D(600/2, 435/2);
		
		assertAll (
			() -> {
				paddle.moveTo(right);
				assertTrue(paddle.handleBoundaryCollision(bounds));
			},
			() -> {
				paddle.moveTo(left);
				assertTrue(paddle.handleBoundaryCollision(bounds));
			},
			() -> {
				paddle.moveTo(center);
				assertFalse(paddle.handleBoundaryCollision(bounds));
			}
		);
		
	}

	@Test
	final void testMoveTo() {
		Point2D testPosition = new Point2D((600/2)-(paddle.getWidth()/2), 435-23);
		
		paddle.moveTo(testPosition);
		assertEquals(paddle.getPosition(), testPosition);
	}

}
