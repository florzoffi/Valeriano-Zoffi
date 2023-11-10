package linea;

public class FinishedStatus extends GameStatus {
    public FinishedStatus(GameBoard gameBoard) {
        super();
    }
    
    public void playAt(Line game, GameBoard gameBoard, int column) {
    	throw new RuntimeException("The game has ended, there is not reason to keep playing");
    }

    @Override
    public boolean isGameFinished() {
        return true; // Game is always considered finished in this state
    }

	@Override
	public char isTurn() {
		return ' ';
	}

	@Override
	public GameStatus playRedAt(Line game, int column) {
		throw new RuntimeException( "The game has ended.");
		
	}

	@Override
	public GameStatus playBlueAt(Line game, int column) {
		throw new RuntimeException( "The game has ended.");
	}
}
