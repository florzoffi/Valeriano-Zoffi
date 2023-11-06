package linea;

import java.util.ArrayList;
import java.util.List;

public class Line {
	public char turn;
	public int base;
	public int height;
	public char c;
	private GameBoard gameBoard;
	public TurnHandler player;
	public GameStatus gameStatus;

	public Line (int base, int height, char c) {
		this.base = base;
		this.height = height;
		this.c = c;
		
		gameBoard = new GameBoard(base, height);
        gameStatus = new OnGoingStatus(gameBoard);
	    player = new RedTurn();
		turn = 'R';
	}

	public boolean finished() {
	    return gameStatus.isGameFinished();
	}
	
	public Line play(String color, int column) {
		if (!player.isTurn(color)) {
			throw new RuntimeException( "It's not the player's turn." );
		}
		
		player.playAt(this, gameBoard, column);
        return this;
	}
	
	public char[] show() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean redTurn() {
		return turn == 'R';
	}
	
	public boolean blueTurn() {
		return turn == 'B';
	}

}
