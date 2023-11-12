package linea;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;


public class LineaTest {
	@Test public void test01SetDimensionsForGame() {
		Line game = gameSet(6, 7, 'A' );
	    assertEquals( 6, game.height );
	    assertEquals( 7, game.base );
	    assertEquals( 'A', game.strategy );
	}
	
	@Test public void test02RedAlwaysPlaysFirst() {
		Line game = gameSet( 6, 7, 'A' );
		turnCheck( game.redTurn(), game.blueTurn() );
	}
		
	@Test public void test03TurnChangesWhenRedPlays() {
		Line game = gameSet( 6, 7, 'A' );
		game.playRedAt( 3 );
		turnCheck( game.blueTurn() ,game.redTurn() );
	}
	
	@Test public void test04TurnChangesWhenBluePlays() {
		Line game = gameSet( 6, 7, 'A' );
		game.playRedAt( 3 );
		game.playBlueAt( 4 );
		turnCheck( game.redTurn() ,game.blueTurn() );
	}
	
	@Test public void test05FailsIfBluePlaysInRedsTurn() {
		Line game = gameSet( 6, 7, 'A' );
		assertThrows( RuntimeException.class, () -> game.playBlueAt(5) );
		turnCheck( game.redTurn() ,game.blueTurn() );
	}
	
	@Test public void test06FailsIfRedPlaysInBluesTurn() {
		Line game = gameSet(6, 7, 'A' );
		game.playRedAt( 3 );
		assertThrows( RuntimeException.class, () -> game.playRedAt(4) );
		turnCheck( game.blueTurn() ,game.redTurn() );
	}
	
	@Test public void test07PlayInLastColumn() {
		Line game = gameSet( 6, 7, 'A' );
		game.playRedAt( 5 );
		turnCheck( game.blueTurn() ,game.redTurn() );
	}
	
	@Test public void test08FailIfInvalidColumnIsPlayed() {
		Line game = gameSet( 6, 7, 'A' );
		assertThrows( RuntimeException.class, () -> game.playRedAt( 8 ) );
		turnCheck( game.redTurn() ,game.blueTurn() );
		consolePrints( game, "Test 8: Invalid column" );
	}

	@Test public void test09FailIfColumnIsFull() {
		Line game = gameSet(6, 4, 'A' );
		
		game.playRedAt( 3 );
		game.playBlueAt( 1 );
		game.playRedAt( 3 );
		game.playBlueAt( 3 );
		game.playRedAt( 3 );
		
		assertThrows( RuntimeException.class, () -> game.playBlueAt( 3 ) );
		consolePrints( game, "Test 9: Full column");
	}
	
	@Test public void test10FailIfNegativeColumnIsPlayed() {
		Line game = gameSet( 6, 4, 'A' );
		assertThrows( RuntimeException.class, () -> game.playBlueAt( -3 ) );
		turnCheck( game.redTurn() ,game.blueTurn() );
	}
	
	@Test public void test11GameFinishedWhenVerticalWin() {
		Line game = gameSet( 6, 7, 'A' );
		
		game.playRedAt( 3 );
		game.playBlueAt( 1 );
		game.playRedAt( 3 );
		game.playBlueAt( 2 );
		game.playRedAt( 3 );
		game.playBlueAt( 4 );
		game.playRedAt( 3 );
		consolePrints( game, "Test 11: Vertical win" );
		gameFinishedAndThisIsWinner( game, 'R' );
	}

	@Test public void test12BlueCanAlsoWin() {
		Line game = gameSet( 6, 7, 'A' );
		
		game.playRedAt( 0 );
		game.playBlueAt( 3 );
		game.playRedAt( 1 );
		game.playBlueAt( 3 );
		game.playRedAt( 1 );
		game.playBlueAt( 3 );
		game.playRedAt( 4 );
		game.playBlueAt( 3 );
		gameFinishedAndThisIsWinner( game, 'B' );
	}
	@Test public void test13GameFinishesWhenDiagonalBottomLeftToTopRightWin() {
		Line game = gameSet( 6, 7, 'B' );
		
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
		consolePrints( game, "Test 13: Diagonal bottom left to top right win" );
		gameFinishedAndThisIsWinner( game, 'R' );
	}
	
	@Test public void test14GameFinishesWhenDiagonalTopLeftToBottomRightWin() {
		Line game = gameSet( 6, 7, 'B' );
		
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
		consolePrints( game, "Test 14: Diagonal Top left to bottom right win" );
		gameFinishedAndThisIsWinner( game, 'R' );
	}
	
	@Test public void test15GameFinishesWhenHorizontalWin() {
		Line game = gameSet( 6, 7, 'A' );
		
		game.playRedAt( 0 );
		game.playBlueAt( 0 );
		game.playRedAt( 1 );
		game.playBlueAt( 1 );
		game.playRedAt( 2 );
		game.playBlueAt( 2 );
		game.playRedAt( 3 );
	
		consolePrints( game, "Test 15: Horizontal win" );
		gameFinishedAndThisIsWinner( game, 'R' );
	}
	
	@Test public void test16GameFinishesWhenDraw() {
		Line game = gameSet( 4, 4, 'A' );
		
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
		consolePrints( game, "Test 16: Draw" );
		assertTrue( game.finished() );
	}
	
	@Test public void test17GameDoesNotFinishIfNoWinOrDraw() {
		Line game = gameSet( 4, 4, 'A' );
		
		game.playRedAt( 0 );
		game.playBlueAt( 0 );

		assertFalse( game.finished() );
		
	}
	@Test public void test18CannotWinWithDiagonalInStrategyA() {
		Line game = gameSet( 6, 7, 'A' );
		
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
		
		assertFalse( game.finished() );
	}
	@Test public void test19CannotHaveAVerticalWinInStrategyB() {
		Line game = gameSet( 6, 7, 'B' );
		
		game.playRedAt( 3 );
		game.playBlueAt( 1 );
		game.playRedAt( 3 );
		game.playBlueAt( 2 );
		game.playRedAt( 3 );
		game.playBlueAt( 4 );
		game.playRedAt( 3 );
	
		assertFalse( game.finished() );
	}
	
	@Test public void test20CannotHaveAHorizontalWinInStrategyB() {
		Line game = gameSet( 6, 7, 'B' );
		
		game.playRedAt( 0 );
		game.playBlueAt( 0 );
		game.playRedAt( 1 );
		game.playBlueAt( 1 );
		game.playRedAt( 2 );
		game.playBlueAt( 2 );
		game.playRedAt( 3 );
	
		assertFalse( game.finished() );
	}
	

	@Test public void test21CanWinWithDiagonalInStrategyC() {
		Line game = gameSet( 6, 7, 'C' );
		
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
		assertEquals( "The game has ended with a winner: R", game.endMessage() );
	}
	@Test public void test22CanHaveAVerticalWinInStrategyC() {
		Line game = gameSet( 6, 7, 'C' );
		
		game.playRedAt( 3 );
		game.playBlueAt( 1 );
		game.playRedAt( 3 );
		game.playBlueAt( 2 );
		game.playRedAt( 3 );
		game.playBlueAt( 4 );
		game.playRedAt( 3 );

		assertTrue( game.finished() );
		assertEquals( "The game has ended with a winner: R", game.endMessage() );
	}
	
	@Test public void test23CanHaveAHorizontalWinInStrategyC() {
		Line game = gameSet( 6, 7, 'C' );
		
		game.playRedAt( 0 );
		game.playBlueAt( 0 );
		game.playRedAt( 1 );
		game.playBlueAt( 1 );
		game.playRedAt( 2 );
		game.playBlueAt( 2 );
		game.playRedAt( 3 );
	
		assertTrue( game.finished() );
	}	
	
	@Test public void test24FailToPlayIfUnknownStrategy() {
		Line game = gameSet( 6, 7, 'F' );
		assertThrows( RuntimeException.class, ()-> game.playRedAt( 5 ) );
	}
	
	@Test public void test25FailToPlayIfEmptyStrategy() {
		Line game = gameSet( 6, 7, ' ' );
		assertThrows( RuntimeException.class, ()-> game.playBlueAt( 4 ) );
	}
	
	@Test public void test26FailIfAskForWinnerWhenNoWin() {
		assertThrows( RuntimeException.class, ()-> gameSet( 6, 7, 'C' ).winner() );
	}
	
	@Test public void test27FailIfAskForEndMessageWhenNoWin() {
		assertThrows( RuntimeException.class, ()-> gameSet( 6, 7, 'C' ).endMessage() );
	}
		
	
	
	private void turnCheck( boolean isTurn,boolean isNotTurn ) {
		assertTrue( isTurn );
		assertFalse( isNotTurn );
	}
	
	private void consolePrints( Line game, String reference ) {
		System.out.println( reference );
		System.out.println( game.show() );
	}
	private Line gameSet( int base, int height, char strategy ) { return new Line( base, height, strategy ); }
	
	private void gameFinishedAndThisIsWinner( Line game, char winner ) {
		assertTrue( game.finished() );
		assertEquals( winner, game.winner() );
	}
	
	}