package linea;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Line {
	public int base;
	public int height;
	public char strategy;
	private GameBoard gameBoard;
	public GameStatus gameStatus;
	private List<Strategy> strategyList;
	private Strategy strategyBeingUsed;

	public Line (int height, int base, char strategy) {
		this.height = height;
		this.base = base;
		this.strategy = strategy;
		
		gameBoard = new GameBoard(base, height);
        gameStatus = new RedPlayerStatus(gameBoard);
        
        strategyList = Arrays.asList(
        		new StrategyA(),
        		new StrategyB(),
        		new StrategyC()
        		);
        
        setStrategy(strategy);
	}
	
	private Line setStrategy(char strategy) {
        strategyBeingUsed = strategyList.stream()
                .filter(strat -> strat.matches(strategy))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown strategy: " + strategy));
        return this;
    }

	public boolean finished() {
	    return gameStatus.isGameFinished();
	}
	
	public char[] show() {
	    return gameBoard.getBoardAsCharArray();
	}

	
	public Line playBlueAt(int column) {
        gameStatus.playBlueAt(this, column);
        strategyBeingUsed.checkWin(column, 'B', this, gameBoard);
        return this;
    }

	public Line playRedAt(int column) {
        gameStatus.playRedAt(this, column);
        strategyBeingUsed.checkWin(column, 'R', this, gameBoard);
        return this;
	}
	
	public boolean redTurn() {
		return gameStatus.isTurn() == 'R';
	}
	
	public boolean blueTurn() {
		return gameStatus.isTurn() == 'B';
	}
}