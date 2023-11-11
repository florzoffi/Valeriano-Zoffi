package linea;

public class FinishedInDraw extends GameStatus {
    private GameBoard gameBoard;

    public FinishedInDraw(GameBoard gameBoard) {
        super();
        this.gameBoard = gameBoard;
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

    public String getEndMessage() {
        return "The game ended in a draw!";
    }
}