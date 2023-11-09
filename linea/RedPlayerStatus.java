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
		
	    if (gameBoard.checkWin(column, 'R', game) || gameBoard.isDraw(game)) {
	        game.gameStatus = new FinishedStatus(gameBoard);
	    } else {
	        game.gameStatus = new BluePlayerStatus(gameBoard);
	    }
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

	@Override
	public char isTurn() {
		return 'R';
	}
}
