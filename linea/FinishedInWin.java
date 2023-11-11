package linea;

public class FinishedInWin extends GameStatus {
    private GameBoard gameBoard;
	private static char winner;
   

    public FinishedInWin(GameBoard gameBoard, char winner) {
        super();
        this.gameBoard = gameBoard;
        this.winner = winner;
    }

    @Override
    public char isTurn() {
        return ' ';
    }

    @Override
    public GameStatus playRedAt(Line game, int column) {
        throw new RuntimeException("The game has ended.");
    }

    @Override
    public GameStatus playBlueAt(Line game, int column) {
        throw new RuntimeException("The game has ended.");
    }

    @Override
    public boolean isGameFinished() {
        return true;
    }

    public static char getWinner() {
        return winner;
    }
}