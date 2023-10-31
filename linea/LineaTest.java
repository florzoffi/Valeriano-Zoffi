package linea;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class LineaTest {
	
	@Test public void test01SetDimensionsForGame() {
		Line line = new Line( 6, 7, ' ' );

	    // No puedo ver la consola,, porq???
	    assertEquals( 6, line.base );
	    assertEquals( 7, line.height );
	    assertEquals( ' ', line.c );
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
		
		game.playRedAt( 3 );
		
		assertTrue( game.blueTurn() );
		assertFalse( game.redTurn() );
	}
	
	@Test public void test06TurnChangesWhenBluePlays() {
		Line game = new Line( 6, 7, ' ' );
		
		game.playRedAt( 3 );
		game.playBlueAt( 4 );
		
		assertTrue( game.redTurn() );
		assertFalse( game.blueTurn() );
	}
	
	@Test public void test07FailsIfBluePlaysInRedsTurn() {
		Line game = new Line( 6, 7, ' ');
		
		assertThrows( RuntimeException.class, () -> game.playBlueAt( 5 ) );
		assertFalse( game.blueTurn() );
		assertTrue( game.redTurn() );
	}
	
	@Test public void test08FailsIfRedPlaysInBluesTurn() {
		Line game = new Line( 6, 7, ' ' );
		 
		game.playRedAt( 3 );
		
		assertThrows( RuntimeException.class, () -> game.playRedAt( 4 ) );
		assertTrue( game.blueTurn() );
		assertFalse( game.redTurn() );
	}
	
	@Test public void test09TurnIsLostWhenInvalidColumnIsPlayed() {
		Line game = new Line( 6, 7, ' ' );
		 
		assertThrows( RuntimeException.class, () -> game.playRedAt( 8 ) );
		assertTrue( game.blueTurn() );
		assertFalse( game.redTurn() );
	}
	
	@Test public void test10TurnIsLostIfColumnIsFull() {
		Line game = new Line( 6, 4, ' ' );
		
		game.playRedAt( 3 );
		game.playBlueAt( 1 );
		game.playRedAt( 3 );
		game.playBlueAt( 3 );
		game.playRedAt( 3 );
		
		assertThrows( RuntimeException.class, () -> game.playBlueAt( 3 ) );
		assertTrue( game.redTurn() );
		assertFalse( game.blueTurn() );
	}
	
	@Test public void test11VerticalWin() {
		Line game = new Line( 6, 7, ' ' );
		
		game.playRedAt( 3 );
		game.playBlueAt( 1 );
		game.playRedAt( 3 );
		game.playBlueAt( 2 );
		game.playRedAt( 3 );
		game.playBlueAt( 4 );
		game.playRedAt( 3 );
		
		assertTrue( game.finished() );
	}
	
	@Test public void test12GameFinishesWhenDiagonalBottomLeftToTopRightWin() {
		Line game = new Line( 6, 7, ' ' );
		
		game.playRedAt( 1 );
		game.playBlueAt( 2 );
		game.playRedAt( 2 );
		game.playBlueAt( 3 );
		game.playRedAt( 3 );
		game.playBlueAt( 4 );
		game.playRedAt( 3 );
		game.playBlueAt( 4 );
		game.playRedAt( 4 );
		game.playBlueAt( 1 );
		game.playRedAt( 4 );
		
		assertTrue( game.finished() );
	}
	
	@Test public void test13GameFinishesWhenDiagonalTopLeftToBottomRightWin() {
		Line game = new Line( 6, 7, ' ' );
		
		game.playRedAt( 5 );
		game.playBlueAt( 4 );
		game.playRedAt( 4 );
		game.playBlueAt( 3 );
		game.playRedAt( 3 );
		game.playBlueAt( 2 );
		game.playRedAt( 3 );
		game.playBlueAt( 2 );
		game.playRedAt( 2 );
		game.playBlueAt( 5 );
		game.playRedAt( 2 );
		
		assertTrue( game.finished() );
	}
	
	@Test public void test14GameFinishesWhenHorizontalWin() {
		Line game = new Line( 6, 7, ' ' );
		
		game.playRedAt( 0 );
		game.playBlueAt( 0 );
		game.playRedAt( 1 );
		game.playBlueAt( 1 );
		game.playRedAt( 2 );
		game.playBlueAt( 2 );
		game.playRedAt( 3 );
		
		assertTrue( game.finished() );
	}
	
	@Test public void test15GameFinishesWhenDraw() {
		Line game = new Line( 4, 4, ' ' );
		
		game.playRedAt( 0 );
		game.playBlueAt( 0 );
		game.playRedAt( 0 );
		game.playBlueAt( 0 );
		game.playRedAt( 1 );
		game.playBlueAt( 1 );
		game.playRedAt( 1 );
		game.playBlueAt( 1 );
		game.playRedAt( 3 );
		game.playBlueAt( 2 );
		game.playRedAt( 2 );
		game.playBlueAt( 3 );
		game.playRedAt( 3 );
		game.playBlueAt( 2 );
		game.playRedAt( 2 );
		game.playBlueAt( 3 );
		
		assertTrue( game.finished() );
	}
	
	@Test public void test16GameDoesNotFinishIfNoWinOrDraw() {
		Line game = new Line( 4, 4, ' ' );
		
		game.playRedAt( 0 );
		game.playBlueAt( 0 );

		assertFalse( game.finished() );
	}
}
