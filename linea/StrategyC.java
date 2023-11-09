package linea;

public class StrategyC extends Strategy {
    @Override
    boolean matches(char strategy) {
        return strategy == 'C';
    }

	@Override
	boolean checkWin(int lastColumnPlayed, char colorPlayed, Line game, GameBoard gameBoard) {
		return gameBoard.horizontalWinCheck(lastColumnPlayed, colorPlayed, game) ||
                gameBoard.verticalWinCheck(lastColumnPlayed, colorPlayed, game) ||
                gameBoard.diagonalBottomLeftToTopRightWinCheck(lastColumnPlayed, colorPlayed, game) || 
        		gameBoard.diagonalTopLeftToBottomRightWinCheck(lastColumnPlayed, colorPlayed, game) ||
        		gameBoard.isDraw(game);
	}   
}