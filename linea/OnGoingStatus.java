package linea;

public class OnGoingStatus extends GameStatus {
    public OnGoingStatus(GameBoard gameBoard) {
        super(gameBoard);
    }

    @Override
    public boolean isGameFinished() {
        char redColor = 'R';
        char blueColor = 'B';

        if (gameBoard.checkHorizontalWin(redColor) || gameBoard.checkVerticalWin(redColor) ||
            gameBoard.checkDiagonalWin(redColor)) {
            return true; // Red player has won
        }

        if (gameBoard.checkHorizontalWin(blueColor) || gameBoard.checkVerticalWin(blueColor) ||
            gameBoard.checkDiagonalWin(blueColor)) {
            return true; // Blue player has won
        }

        if (gameBoard.isBoardFull()) {
            return true; // It's a draw
        }

        return false; // Game is still ongoing
    }
}