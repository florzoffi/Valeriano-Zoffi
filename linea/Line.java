package linea;

import java.util.ArrayList;
import java.util.List;

public class Line {
	public char turn;
	public int base;
	public int height;
	public char c;
	public List<List<Coordinate>> grid;
	public Player player;
	public GameStatus gameStatus;

	public Line (int base, int height, char c) {
		if (base < 4 || height < 4) {
			throw new RuntimeException( "To ensure every type of win is available, dimensions of four or above are required" );
		}
		
		this.base = base;
		this.height = height;
		this.c = c;
		
		grid = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            List<Coordinate> rowList = new ArrayList<>();
            for (int x = 0; x < base; x++) {
                rowList.add(new Coordinate(x, y, c, this));
            }
            grid.add(rowList);
        }
	    
        gameStatus = new GameStatus(this);
	    player = new RedPlayer();
		turn = 'R';
	}

	public boolean finished() {
	    return gameStatus.isGameFinished();
	}
	
	public Line play(String color, int column) {
		if (!player.isTurn(color)) {
			throw new RuntimeException( "It's not the player's turn." );
		}
		
		player.playAt(this, column);
        
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
