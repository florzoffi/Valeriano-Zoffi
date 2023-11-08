package linea;

public class BluePlayerStatus extends GameStatus {
	private Line game;
	private GameBoard gameBoard;
	int lastPlayedColumn;
	int lastPlayedRow;
	
	public BluePlayerStatus(GameBoard gameBoard) {
		super();
		this.gameBoard = gameBoard;
	}

	@Override
	public void playBlueAt(Line game, GameBoard gameBoard, int column) {
        if (column < 0 || column >= gameBoard.getNumColumns() || gameBoard.isColumnFull(column)) {
            throw new RuntimeException("Invalid move. Your turn has been lost");
        }

        gameBoard.placeChip(column, 'B');
        lastPlayedColumn = column;
        lastPlayedRow = gameBoard.getColumnHeight(column);
        
        if (isGameFinished()) {
        	game.gameStatus = new FinishedStatus(gameBoard);
        }
		
	}
	
	@Override
	public void playRedAt(Line game, GameBoard gameBoard, int column) {
		throw new RuntimeException("It's not the player's turn.");
	}

	@Override
	public boolean isGameFinished() {
		if (gameBoard.checkHorizontalWin('B') || gameBoard.checkVerticalWin('B') ||
	            gameBoard.checkDiagonalWin('B', lastPlayedRow, lastPlayedColumn)) {
			game.gameStatus = new FinishedStatus(gameBoard);
	            return true; 
	        }
		return false;
	}

	@Override
	public char isTurn() {
		return 'B';
	}

	@Override
	public void setTurn() {
		game.gameStatus = new RedPlayerStatus(gameBoard);
	}
	
	

}
