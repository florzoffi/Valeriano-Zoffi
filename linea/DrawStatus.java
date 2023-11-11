package linea;

public class DrawStatus extends GameStatus {
    public DrawStatus(GameBoard gameBoard) {
        super();
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