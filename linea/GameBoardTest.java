package linea;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class GameBoardTest {
	@Test public void test01GameBoardIsEmptyWhenCreated() {
		GameBoard gameBoard = new GameBoard(6, 7);
		assertFalse(gameBoard.isBoardFull());
	}
	
	@Test public void test02PlacedChipIsInCell() {
		GameBoard gameBoard = new GameBoard(6, 7);
		gameBoard.placeChip(0, 'B');
		assertEquals('B', gameBoard.insideCell(0, 0) );
	}
	
	@Test public void test03PlaceChipInLastColumnCheck() {
		GameBoard gameBoard = new GameBoard(6, 6);
		gameBoard.placeChip(5, 'R');
		assertEquals('R', gameBoard.insideCell(0, 5));
	}
	
	@Test public void test04CannotPlaceChipOnFullColumn() {
		GameBoard gameBoard = new GameBoard(2,2);
		gameBoard.placeChip(1, 'R');
		gameBoard.placeChip(1, 'B');
		
		assertFalse( gameBoard.spaceInColumnLeft(1) );
		assertThrows( RuntimeException.class, () -> gameBoard.placeChip(1, 'R') );
	}
	
	@Test public void test05FailIfColumnGivenIsGreaterThanBase() {
		GameBoard gameBoard = new GameBoard(2,2);
		assertThrows( RuntimeException.class, () -> gameBoard.placeChip(5, 'R'));
	}
}
