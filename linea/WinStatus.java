package linea;

public class WinStatus extends GameStatus {
    private static char winner;
   

    public WinStatus(GameBoard gameBoard, char winner) {
        super();
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