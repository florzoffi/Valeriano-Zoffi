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
	    
	    player = new RedPlayer();
		turn = 'R';
	}

	public boolean finished() {
	    // Check for a vertical win
	    for (int x = 0; x < base; x++) {
	        for (int y = 0; y <= height - 4; y++) {
	            char chip = grid.get(y).get(x).getValue();
	            if (chip != c && grid.get(y + 1).get(x).getValue() == chip &&
	                grid.get(y + 2).get(x).getValue() == chip && grid.get(y + 3).get(x).getValue() == chip) {
	                return true; // Vertical win detected
	            }
	        }
	    }

	    // Check for a diagonal win (bottom-left to top-right)
	    for (int x = 0; x <= base - 4; x++) {
	        for (int y = 0; y <= height - 4; y++) {
	            char chip = grid.get(y).get(x).getValue();
	            if (chip != c && grid.get(y + 1).get(x + 1).getValue() == chip &&
	                grid.get(y + 2).get(x + 2).getValue() == chip && grid.get(y + 3).get(x + 3).getValue() == chip) {
	                return true; // Diagonal win detected (bottom-left to top-right)
	            }
	        }
	    }

	    // Check for a diagonal win (top-left to bottom-right)
	    for (int x = 0; x <= base - 4; x++) {
	        for (int y = 3; y < height; y++) {
	            char chip = grid.get(y).get(x).getValue();
	            if (chip != c && grid.get(y - 1).get(x + 1).getValue() == chip &&
	                grid.get(y - 2).get(x + 2).getValue() == chip && grid.get(y - 3).get(x + 3).getValue() == chip) {
	                return true; // Diagonal win detected (top-left to bottom-right)
	            }
	        }
	    }

	    // Check for a horizontal win
	    for (int y = 0; y < height; y++) {
	        for (int x = 0; x <= base - 4; x++) {
	            char chip = grid.get(y).get(x).getValue();
	            if (chip != c && grid.get(y).get(x + 1).getValue() == chip &&
	                grid.get(y).get(x + 2).getValue() == chip && grid.get(y).get(x + 3).getValue() == chip) {
	                return true; // Horizontal win detected
	            }
	        }
	    }

	    for (int y = 0; y < height; y++) {
	        for (int x = 0; x <= base - 4; x++) {
	            for (int i = 0; i < 4; i++) {
	                if (grid.get(y).get(x + i).getValue() == c) {
	                    break; // This sequence has an empty cell, move to the next sequence
	                }
	                if (i == 3) {
	                    return true; // All four cells in this sequence are not empty, it's not a draw
	                }
	            }
	        }
	    }

	    return false; // The game is not a draw
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
