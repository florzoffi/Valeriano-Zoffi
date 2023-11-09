package linea;

public class Line {
	public int base;
	public int height;
	public char c;
	private GameBoard gameBoard;
	public GameStatus gameStatus;

	public Line (int height, int base, char c) {
		this.height = height;
		this.base = base;
		this.c = c;
		
		gameBoard = new GameBoard(base, height);
        gameStatus = new RedPlayerStatus(gameBoard);
	}

	public boolean finished() {
	    return gameStatus.isGameFinished();
	}
	
	public char[] show() {
	    return gameBoard.getBoardAsCharArray();
	}

	
	public Line playBlueAt(int column) {
        gameStatus.playBlueAt(this, column);
        return this;
    }

	public Line playRedAt(int column) {
        gameStatus.playRedAt(this, column);
        return this;
	}
	
	public boolean redTurn() {
		return gameStatus.isTurn() == 'R';
	}
	
	public boolean blueTurn() {
		return gameStatus.isTurn() == 'B';
	}

}