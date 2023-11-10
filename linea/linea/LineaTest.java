package linea;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class LineaTest {
	
	@Test public void test01SetDimensionsForGame() {
		Line game = new Line( 6, 7, 'A' );

	    assertEquals( 6, game.height );
	    assertEquals( 7, game.base );
	    assertEquals( 'A', game.strategy );
	}
	
	@Test public void test02RedAlwaysPlaysFirst() {
		Line game = new Line( 6, 7, 'A');
		assertTrue( game.redTurn() );
		assertFalse( game.blueTurn() );
	}
	
	@Test public void test03TurnChangesWhenRedPlays() {
		Line game = new Line( 6, 7, 'A' );
		
		game.playRedAt(3);
		
		assertTrue( game.blueTurn() );
		assertFalse( game.redTurn() );
	}
	
	@Test public void test04TurnChangesWhenBluePlays() {
		Line game = new Line( 6, 7, 'A' );
		
		game.playRedAt(3);
		game.playBlueAt(4);
		
		assertTrue( game.redTurn() );
		assertFalse( game.blueTurn() );
	}
	
	@Test public void test05FailsIfBluePlaysInRedsTurn() {
		Line game = new Line( 6, 7, 'A');
		
		assertThrows( RuntimeException.class, () -> game.playBlueAt(5) );
		assertFalse( game.blueTurn() );
		assertTrue( game.redTurn() );
	}
	
	@Test public void test06FailsIfRedPlaysInBluesTurn() {
		Line game = new Line( 6, 7, 'A' );
		 
		game.playRedAt(3);
		
		assertThrows( RuntimeException.class, () -> game.playRedAt(4) );
		assertTrue( game.blueTurn() );
		assertFalse( game.redTurn() );
	}
	
	@Test public void test07FailIfInvalidColumnIsPlayed() {
		Line game = new Line( 6, 7, 'A' );
		
		assertThrows( RuntimeException.class, () -> game.playRedAt(8) );
		assertFalse( game.blueTurn() );
		assertTrue( game.redTurn() );
		System.out.println("Test 10: Invalid column");
		System.out.println(game.show());
		
	}
	
	@Test public void test8FailIfColumnIsFull() {
		Line game = new Line( 6, 4, 'A' );
		
		game.playRedAt(3);
		game.playBlueAt(1);
		game.playRedAt(3);
		game.playBlueAt(3);
		game.playRedAt(3);
		
		assertThrows( RuntimeException.class, () -> game.playBlueAt(3) );
		System.out.println("Test 10: Full column");
		System.out.println(game.show());
	}
	
	@Test public void test9GameFinishedWhenVerticalWin() {
		Line game = new Line( 6, 7, 'A' );
		
		game.playRedAt(3);
		game.playBlueAt(1);
		game.playRedAt(3);
		game.playBlueAt(2);
		game.playRedAt(3);
		game.playBlueAt(4);
		game.playRedAt(3);
		System.out.println("Test 11: vertical win");
		System.out.println(game.show());
		assertTrue( game.finished() );
	}
	
	@Test public void test10GameFinishesWhenDiagonalBottomLeftToTopRightWin() {
		Line game = new Line( 6, 7, 'B' );
		
		game.playRedAt(1);
		game.playBlueAt(2);
		game.playRedAt(2);
		game.playBlueAt(3);
		game.playRedAt(3);
		game.playBlueAt(4);
		game.playRedAt(3);
		game.playBlueAt(4);
		game.playRedAt(4);
		game.playBlueAt(1);
		game.playRedAt(4);
		System.out.println("Test 12: Diagonal bottom left to top right win");
		System.out.println(game.show());
		assertTrue( game.finished() );
	}
	
	@Test public void test11GameFinishesWhenDiagonalTopLeftToBottomRightWin() {
		Line game = new Line( 6, 7, 'B' );
		
		game.playRedAt(5);
		game.playBlueAt(4);
		game.playRedAt(4);
		game.playBlueAt(3);
		game.playRedAt(3);
		game.playBlueAt(2);
		game.playRedAt(3);
		game.playBlueAt(2);
		game.playRedAt(2);
		game.playBlueAt(5);
		game.playRedAt(2);
		System.out.println("Test 13: Diagonal Top left to bottom right win");
		System.out.println(game.show());
		assertTrue( game.finished() );
	}
	
	@Test public void test12GameFinishesWhenHorizontalWin() {
		Line game = new Line( 6, 7, 'A' );
		
		game.playRedAt(0);
		game.playBlueAt(0);
		game.playRedAt(1);
		game.playBlueAt(1);
		game.playRedAt(2);
		game.playBlueAt(2);
		game.playRedAt(3);
		System.out.println("Test 14: Horizontal win");
		System.out.println(game.show());
		assertTrue( game.finished() );
	}
	
	@Test public void test13GameFinishesWhenDraw() {
		Line game = new Line( 4, 4, 'A' );
		
		game.playRedAt(0);
		game.playBlueAt(0);
		game.playRedAt(0);
		game.playBlueAt(0);
		game.playRedAt(1);
		game.playBlueAt(1);
		game.playRedAt(1);
		game.playBlueAt(1);
		game.playRedAt(3);
		game.playBlueAt(2);
		game.playRedAt(2);
		game.playBlueAt(3);
		game.playRedAt(3);
		game.playBlueAt(2);
		game.playRedAt(2);
		game.playBlueAt(3);
		System.out.println("Test 15: Draw");
		System.out.println(game.show());
		assertTrue( game.finished() );
	}
	
	@Test public void test14GameDoesNotFinishIfNoWinOrDraw() {
		Line game = new Line( 4, 4, 'A' );
		
		game.playRedAt(0);
		game.playBlueAt(0);

		assertFalse( game.finished() );
	}
	@Test public void test15CannotWinWithDiagonalInStrategyA() {
		Line game = new Line( 6, 7, 'A' );
		
		game.playRedAt(5);
		game.playBlueAt(4);
		game.playRedAt(4);
		game.playBlueAt(3);
		game.playRedAt(3);
		game.playBlueAt(2);
		game.playRedAt(3);
		game.playBlueAt(2);
		game.playRedAt(2);
		game.playBlueAt(5);
		game.playRedAt(2);
		
		assertFalse( game.finished() );
	}
	@Test public void test16CannotHaveAVerticalWinInStrategyB() {
		Line game = new Line( 6, 7, 'B' );
		
		game.playRedAt(3);
		game.playBlueAt(1);
		game.playRedAt(3);
		game.playBlueAt(2);
		game.playRedAt(3);
		game.playBlueAt(4);
		game.playRedAt(3);
	
		assertFalse( game.finished() );
	}
	
	@Test public void test17GCannotHaveAHorizontalWinInStrategyB() {
		Line game = new Line( 6, 7, 'B' );
		
		game.playRedAt(0);
		game.playBlueAt(0);
		game.playRedAt(1);
		game.playBlueAt(1);
		game.playRedAt(2);
		game.playBlueAt(2);
		game.playRedAt(3);
	
		assertFalse( game.finished() );
	}
	

	@Test public void test18CanWinWithDiaonalInStrategyC() {
		Line game = new Line( 6, 7, 'C' );
		
		game.playRedAt(5);
		game.playBlueAt(4);
		game.playRedAt(4);
		game.playBlueAt(3);
		game.playRedAt(3);
		game.playBlueAt(2);
		game.playRedAt(3);
		game.playBlueAt(2);
		game.playRedAt(2);
		game.playBlueAt(5);
		game.playRedAt(2);
	
		assertTrue( game.finished() );
	}
	@Test public void test19CanHaveAVerticalWinInStrategyC() {
		Line game = new Line( 6, 7, 'C' );
		
		game.playRedAt(3);
		game.playBlueAt(1);
		game.playRedAt(3);
		game.playBlueAt(2);
		game.playRedAt(3);
		game.playBlueAt(4);
		game.playRedAt(3);

		assertTrue( game.finished() );
	}
	
	@Test public void test20GCanHaveAHorizontalWinInStrategyC() {
		Line game = new Line( 6, 7, 'C' );
		
		game.playRedAt(0);
		game.playBlueAt(0);
		game.playRedAt(1);
		game.playBlueAt(1);
		game.playRedAt(2);
		game.playBlueAt(2);
		game.playRedAt(3);
	
		assertTrue( game.finished() );
	}	
		
	}