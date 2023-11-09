package linea;

public class StrategyB extends Strategy {
    @Override
    boolean matches(char strategy) {
        return strategy == 'B';
    }

	@Override
	boolean checkWin(int lastColumnPlayed, char colorPlayed, Line game, GameBoard gameBoard) {
		return gameBoard.diagonalBottomLeftToTopRightWinCheck(lastColumnPlayed, colorPlayed, game) || 
        		gameBoard.diagonalTopLeftToBottomRightWinCheck(lastColumnPlayed, colorPlayed, game) || 
        		gameBoard.isDraw(game);
	}
}