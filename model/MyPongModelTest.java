package model;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.HashSet;
import java.util.Set;
import model.Input;
import model.BarKey;
import model.MyPongModel;
import model.Input.Dir;



import java.awt.Dimension;
import java.awt.Point;




public class MyPongModelTest {

	@Test
	public void testgetFieldSize() {
	MyPongModel test = new MyPongModel();
	assertEquals(test.getFieldSize(), new Dimension(1200, 600));
	}
	@Test
	public void testgetScore(){
	MyPongModel test = new MyPongModel();
	assertEquals(test.getScore(BarKey.LEFT), "0");
	}
	
	@Test
	public void testgetBarHeight(){
	MyPongModel test = new MyPongModel();
	assertEquals(test.getBarHeight(BarKey.RIGHT), 200);		
	}
	
	@Test
	public void testgetBarPos(){
	MyPongModel test = new MyPongModel();
	assertEquals(test.getBarPos(BarKey.RIGHT), 300);
	}
	
	@Test
	public void testgetMessage(){
		MyPongModel test = new MyPongModel();
		assertEquals(test.getMessage(), "Pong");
	}
	@Test	
	public void testgetBallPos(){
		Point testPoint = new Point(600,300);
		MyPongModel test = new MyPongModel();
		assertEquals(test.getBallPos(),testPoint);
	}
	@Test
	public void testcompute(){
		Set<Input> testHashSet = new HashSet<Input>();
		Set<Input> testInput = new HashSet<Input>();
		MyPongModel tester = new MyPongModel("lefPlayer", "rightPlayer");
		
		tester.compute(testInput, 1000/30);
		assertEquals(tester.getBallPos(), new Point(605,300));
		
		for(int i = 0; i < 114; i++){
			tester.compute(testInput, 1000/30);
		}
		assertEquals(tester.getBallPos(), new Point(1150, 300)); /* checks if the ball turns and increases speed */
		for(int j = 0; j < 111; j++){
			tester.compute(testInput, 1000/30);
		}
		assertEquals(tester.getBallPos(), new Point(40, 300));
		
		tester.compute(testInput, 1000/30);
		/*assertEquals(tester.getBallPos(), new Point(20,300));
		*/
		
		assertTrue(tester.getXSpeed() == -10);
		assertTrue(tester.getBallPos().getX() == 30);
		
		tester.compute(testInput, 1000/30);
		
		assertTrue(tester.getXSpeed() == 15);
		assertTrue(tester.getBallPos().getX() == 60);
		
		
		for(int k = 0; k < 74; k++){
			tester.compute(testInput, 1000/30);
		}
		
		
	}
}
