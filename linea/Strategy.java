package linea;

import java.util.List;

public abstract class Strategy {

    static public Strategy strategyFor( char strategy ) {
        return List.of(
                new StrategyA(),
                new StrategyB(),
                new StrategyC()
        ).stream()
                .filter(strat -> strat.matches( strategy ))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unknown strategy: " + strategy));
    }

    abstract boolean matches( char strategy );

    abstract boolean checkWin( int lastColumnPlayed, char colorPlayed, Line game, GameBoard gameBoard );
}