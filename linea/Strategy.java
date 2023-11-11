package linea;

import java.util.List;

public abstract class Strategy {

    static public Strategy strategyFor(char strategy) {
        return List.of(
                new StrategyA(),
                new StrategyB(),
                new StrategyC()
        ).stream()
                .filter(strat -> strat.matches(strategy))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unknown strategy: " + strategy));
    }

    abstract boolean matches(char strategy);

    abstract boolean checkWin(int lastColumnPlayed, char colorPlayed, Line game, GameBoard gameBoard);
}

class StrategyA extends Strategy {
    @Override
    boolean matches(char strategy) {
        return strategy == 'A';
    }

    @Override
    boolean checkWin(int lastColumnPlayed, char colorPlayed, Line game, GameBoard gameBoard) {
        return gameBoard.horizontalWinCheck(lastColumnPlayed, colorPlayed, game) ||
                gameBoard.verticalWinCheck(lastColumnPlayed, colorPlayed, game) ||
                gameBoard.isDraw(game);
    }
}

class StrategyB extends Strategy {
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

class StrategyC extends Strategy {
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