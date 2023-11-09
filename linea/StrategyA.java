package linea;

public class StrategyA extends Strategy {
    @Override
    boolean matches(char strategy) {
        return strategy == 'A';
    }

	@Override
	boolean checkWin(int lastColumnPlayed, char colorPlayed, Line game, GameBoard gameBoard) {
		return gameBoard.horizontalWinCheck(lastColumnPlayed, colorPlayed, game) || gameBoard.verticalWinCheck(lastColumnPlayed, colorPlayed, game) || gameBoard.isDraw(game);
	}
}