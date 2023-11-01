package linea;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class LineaTest {
	
	@Test public void test01SetDimensionsForGame() {
		Line game = new Line( 6, 7, ' ' );

	    // No puedo ver la consola,, porq???
	    assertEquals( 6, game.base );
	    assertEquals( 7, game.height );
	    assertEquals( ' ', game.c );
	}
	
	@Test public void test02FailsIfHeightIsLessThanFour() {
		assertThrows( RuntimeException.class, () -> new Line(6, 3, ' ') );
	}
	
	@Test public void test03FailsIfBaseIsLessThanFour() {
		assertThrows( RuntimeException.class, () -> new Line(2, 5, ' ') );
	}
	
	@Test public void test04RedAlwaysPlaysFirst() {
		Line game = new Line( 6, 7, ' ');
		assertTrue( game.redTurn() );
		assertFalse( game.blueTurn() );
	}
	
	@Test public void test05TurnChangesWhenRedPlays() {
		Line game = new Line( 6, 7, ' ' );
		
		game.play("Red", 3 );
		
		assertTrue( game.blueTurn() );
		assertFalse( game.redTurn() );
	}
	
	@Test public void test06TurnChangesWhenBluePlays() {
		Line game = new Line( 6, 7, ' ' );
		
		game.play("Red", 3 );
		game.play("Blue", 4 );
		
		assertTrue( game.redTurn() );
		assertFalse( game.blueTurn() );
	}
	
	@Test public void test07FailsIfBluePlaysInRedsTurn() {
		Line game = new Line( 6, 7, ' ');
		
		assertThrows( RuntimeException.class, () -> game.play("Blue", 5 ) );
		assertFalse( game.blueTurn() );
		assertTrue( game.redTurn() );
	}
	
	@Test public void test08FailsIfRedPlaysInBluesTurn() {
		Line game = new Line( 6, 7, ' ' );
		 
		game.play("Red", 3 );
		
		assertThrows( RuntimeException.class, () -> game.play("Red", 4 ) );
		assertTrue( game.blueTurn() );
		assertFalse( game.redTurn() );
	}
	
	@Test public void test09TurnIsLostWhenInvalidColumnIsPlayed() {
		Line game = new Line( 6, 7, ' ' );
		
		assertThrows( RuntimeException.class, () -> game.play("Red", 8 ) );
		assertTrue( game.blueTurn() );
		assertFalse( game.redTurn() );
	}
	
	@Test public void test10TurnIsLostIfColumnIsFull() {
		Line game = new Line( 6, 4, ' ' );
		
		game.play("Red", 3 );
		game.play("Blue", 1 );
		game.play("Red", 3 );
		game.play("Blue", 3 );
		game.play("Red", 3 );
		
		assertThrows( RuntimeException.class, () -> game.play("Blue", 3 ) );
		assertTrue( game.redTurn() );
		assertFalse( game.blueTurn() );
	}
	
	@Test public void test11GameFinishedWhenVerticalWin() {
		Line game = new Line( 6, 7, ' ' );
		
		game.play("Red", 3 );
		game.play("Blue", 1 );
		game.play("Red", 3 );
		game.play("Blue", 2 );
		game.play("Red", 3 );
		game.play("Blue", 4 );
		game.play("Red", 3 );
		
		assertTrue( game.finished() );
	}
	
	@Test public void test12GameFinishesWhenDiagonalBottomLeftToTopRightWin() {
		Line game = new Line( 6, 7, ' ' );
		
		game.play("Red", 1 );
		game.play("Blue", 2 );
		game.play("Red", 2 );
		game.play("Blue", 3 );
		game.play("Red", 3 );
		game.play("Blue", 4 );
		game.play("Red", 3 );
		game.play("Blue", 4 );
		game.play("Red", 4 );
		game.play("Blue", 1 );
		game.play("Red", 4 );
		
		assertTrue( game.finished() );
	}
	
	@Test public void test13GameFinishesWhenDiagonalTopLeftToBottomRightWin() {
		Line game = new Line( 6, 7, ' ' );
		
		game.play("Red", 5 );
		game.play("Blue", 4 );
		game.play("Red", 4 );
		game.play("Blue", 3 );
		game.play("Red", 3 );
		game.play("Blue", 2 );
		game.play("Red", 3 );
		game.play("Blue", 2 );
		game.play("Red", 2 );
		game.play("Blue", 5 );
		game.play("Red", 2 );
		
		assertTrue( game.finished() );
	}
	
	@Test public void test14GameFinishesWhenHorizontalWin() {
		Line game = new Line( 6, 7, ' ' );
		
		game.play("Red", 0 );
		game.play("Blue", 0 );
		game.play("Red", 1 );
		game.play("Blue", 1 );
		game.play("Red", 2 );
		game.play("Blue", 2 );
		game.play("Red", 3 );
		
		assertTrue( game.finished() );
	}
	
	@Test public void test15GameFinishesWhenDraw() {
		Line game = new Line( 4, 4, ' ' );
		
		game.play("Red", 0 );
		game.play("Blue", 0 );
		game.play("Red", 0 );
		game.play("Blue", 0 );
		game.play("Red", 1 );
		game.play("Blue", 1 );
		game.play("Red", 1 );
		game.play("Blue", 1 );
		game.play("Red", 3 );
		game.play("Blue", 2 );
		game.play("Red", 2 );
		game.play("Blue", 3 );
		game.play("Red", 3 );
		game.play("Blue", 2 );
		game.play("Red", 2 );
		game.play("Blue", 3 );
		
		assertTrue( game.finished() );
	}
	
	@Test public void test16GameDoesNotFinishIfNoWinOrDraw() {
		Line game = new Line( 4, 4, ' ' );
		
		game.play("Red", 0 );
		game.play("Blue", 0 );

		assertFalse( game.finished() );
	}
}
