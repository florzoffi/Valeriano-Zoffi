package linea;

public class Line {
	public int base;
	public int height;
	public char strategy;
	private GameBoard gameBoard;
	public GameStatus gameStatus;

	public Line (int height, int base, char strategy) {
		this.height = height;
		this.base = base;
		this.strategy = strategy;
		
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
        Strategy.strategyFor(strategy).checkWin(column, 'B', this, gameBoard);
        return this;
    }

	public Line playRedAt(int column) {
        gameStatus.playRedAt(this, column);
        Strategy.strategyFor(strategy).checkWin(column, 'R', this, gameBoard);
        return this;
	}
	
	public boolean redTurn() {
		return gameStatus.isTurn() == 'R';
	}
	
	public boolean blueTurn() {
		return gameStatus.isTurn() == 'B';
	}
}