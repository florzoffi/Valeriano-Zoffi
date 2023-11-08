package linea;

public class FinishedStatus extends GameStatus {
    public FinishedStatus(GameBoard gameBoard) {
        super(gameBoard);
    }
    
    public void playAt(Line game, GameBoard gameBoard, int column) {
    	throw new RuntimeException("The game has ended, there is not reason to keep playing");
    }

    @Override
    public boolean isGameFinished() {
        return true; // Game is always considered finished in this state
    }

	@Override
	public void switchTurn() {
		// TODO Auto-generated method stub
	}

	@Override
	public char isTurn() {
		return ' ';
	}

	@Override
	public void playRedAt(Line game, GameBoard gameBoard, int column) {
		throw new RuntimeException( "The game has ended.");
		
	}

	@Override
	public void playBlueAt(Line game, GameBoard gameBoard, int column) {
		throw new RuntimeException( "The game has ended.");
	}
}
