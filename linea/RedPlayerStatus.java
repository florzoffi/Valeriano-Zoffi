package linea;

public class RedPlayerStatus extends GameStatus {
	private Line game;
	private GameBoard gameBoard;
	int lastPlayedColumn;
	int lastPlayedRow;
	
	public RedPlayerStatus(GameBoard gameBoard) {
		super();
        this.gameBoard = gameBoard;
	}

	@Override
	public void playRedAt(Line game, GameBoard gameBoard, int column) {
        if (column < 0 || column >= gameBoard.getNumColumns() || gameBoard.isColumnFull(column)) {
            throw new RuntimeException("Invalid move. Your turn has been lost");
        }

        gameBoard.placeChip(column, 'R');
        lastPlayedColumn = column;
        lastPlayedRow = gameBoard.getColumnHeight(column);
        
        if (isGameFinished()) {
        	game.gameStatus = new FinishedStatus(gameBoard);
        }
        
	}
	
	@Override
	public void playBlueAt(Line game, GameBoard gameBoard, int column) {
		throw new RuntimeException("It's not the players turn.");
	}

	@Override
	public boolean isGameFinished() {
		if (gameBoard.checkHorizontalWin('R') || gameBoard.checkVerticalWin('R') ||
	            gameBoard.checkDiagonalWin('R', lastPlayedRow, lastPlayedColumn)) {
			game.gameStatus = new FinishedStatus(gameBoard);
	            return true; 
	        }
		return false;
	}

	@Override
	public char isTurn() {
		return 'R';
	}

	@Override
	public void setTurn() {
		game.gameStatus = new RedPlayerStatus(gameBoard);
	}
}
