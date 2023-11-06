package linea;

public abstract class GameStatus {
    protected final GameBoard gameBoard;

    public GameStatus(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public abstract boolean isGameFinished();
}