package linea;

public class BluePlayerStatus extends GameStatus {
	private GameBoard gameBoard;
	int lastPlayedColumn;
	int lastPlayedRow;
	
	public BluePlayerStatus(GameBoard gameBoard) {
		super();
		this.gameBoard = gameBoard;
	}

	@Override
	public GameStatus playBlueAt(Line game, int column) {
		gameBoard.placeChip(column, 'B');
		game.gameStatus = new RedPlayerStatus(gameBoard);
		return this;
	}
	
	@Override
	public GameStatus playRedAt(Line game, int column) {
		throw new RuntimeException("It's not the player's turn.");
	}

	@Override
	public boolean isGameFinished() {
		return false;
	}

	@Override
	public char isTurn() {
		return 'B';
	}	
}