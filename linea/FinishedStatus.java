package linea;

public class FinishedStatus extends GameStatus {
    public FinishedStatus(GameBoard gameBoard) {
        super(gameBoard);
    }

    @Override
    public boolean isGameFinished() {
        return true; // Game is always considered finished in this state
    }
}