package linea;

public class WinStatus extends GameStatus {
    private char winner;

    public WinStatus( GameBoard gameBoard, char winner ) {
        super();
        this.winner = winner;
    }

    @Override
    public char isTurn() {
        return ' ';
    }

    @Override
    public GameStatus playRedAt( Line game, int column ) {
        throw new RuntimeException( gameEnded );
    }

    @Override
    public GameStatus playBlueAt(Line game, int column) {
        throw new RuntimeException( gameEnded );
    }

    @Override
    public boolean isGameFinished() {
        return true;
    }

    public char getWinner() {
        return winner;
    }

	@Override
	public String getEndMessage() {
		return "The game has ended with a winner: " + winner;
	}
}