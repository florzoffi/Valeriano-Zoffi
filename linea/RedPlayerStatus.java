package linea;

public class RedPlayerStatus extends GameStatus {
	private GameBoard gameBoard;
	int lastPlayedColumn;
	int lastPlayedRow;
	
	public RedPlayerStatus(GameBoard gameBoard) {
		super();
        this.gameBoard = gameBoard;
	}

	@Override
	public GameStatus playRedAt(Line game, int column) {
		gameBoard.placeChip(column, 'R');
		
		game.gameStatus = new BluePlayerStatus(gameBoard);
		
		gameBoard.verticalWinCheck(column, 'R', game); // todos los otros checks abajo
		
		return this;

     	}
	
	@Override
	public GameStatus playBlueAt(Line game, int column) {
		throw new RuntimeException("It's not the players turn.");
	}

	@Override
	public boolean isGameFinished() {
		return false;
	}

	@Override
	public char isTurn() {
		return 'R';
	}
}