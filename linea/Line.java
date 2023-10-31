package linea;

public class Line {
	public char turn;
	public int base;
	public int height;
	public char c;
	public char[][] grid;
	public Player player;

	public Line (int base, int height, char c) {
		if (base < 4) {
			throw new RuntimeException( "To ensure every type of win is available, dimensions of four or above are required" );
		}
		this.base = base;
		if (height < 4) {
			throw new RuntimeException( "To ensure every type of win is available, dimensions of four or above are required" );
		}
		this.height = height;
		this.c = c;
		
		grid = new char[height][base];
	    for (int row = 0; row < height; row++) {
	        for (int col = 0; col < base; col++) {
	            grid[row][col] = c;
	        }
	    }
	    
	    player = new RedPlayer();
		turn = 'R';
	}

	public boolean finished() {
		// Check for a vertical win
	    for (int col = 0; col < base; col++) {
	        for (int row = 0; row <= height - 4; row++) {
	            char chip = grid[row][col];
	            if (chip != c && grid[row + 1][col] == chip && grid[row + 2][col] == chip && grid[row + 3][col] == chip) {
	                return true; // Vertical win detected
	            }
	        }
	    }
	    
	    // Check for a diagonal win (bottom-left to top-right)
	    for (int col = 0; col <= base - 4; col++) {
	        for (int row = 0; row <= height - 4; row++) {
	            char chip = grid[row][col];
	            if (chip != c && grid[row + 1][col + 1] == chip && grid[row + 2][col + 2] == chip && grid[row + 3][col + 3] == chip) {
	                return true; // Diagonal win detected (bottom-left to top-right)
	            }
	        }
	    }

	    // Check for a diagonal win (top-left to bottom-right)
	    for (int col = 0; col <= base - 4; col++) {
	        for (int row = 3; row < height; row++) {
	            char chip = grid[row][col];
	            if (chip != c && grid[row - 1][col + 1] == chip && grid[row - 2][col + 2] == chip && grid[row - 3][col + 3] == chip) {
	                return true; // Diagonal win detected (top-left to bottom-right)
	            }
	        }
	    }
	    
	    // Check for a horizontal win
	    for (int row = 0; row < height; row++) {
	        for (int col = 0; col <= base - 4; col++) {
	            char chip = grid[row][col];
	            if (chip != c && grid[row][col + 1] == chip && grid[row][col + 2] == chip && grid[row][col + 3] == chip) {
	                return true; // Horizontal win detected
	            }
	        }
	    }
	    
	    // If no win condition is met, check for a draw (board is completely filled)
	    for (int col = 0; col < base; col++) {
	        if (!isColumnFull(col)) {
	            return false; // The game is not finished
	        }
	    }
	    
	    return true; // The game is a draw
	}

	public void playRedAt(int column) {
		if (!redTurn()) {
	        throw new RuntimeException( "It's not the player's turn." );
	    }

        if (column < 0 || column >= base || isColumnFull(column)) {
        	player.setTurn(this);
            throw new RuntimeException("Invalid move. Your turn has been lost");
        }
        
        player.playAt(this, column);
        player.setTurn(this);
	}

	public char[] show() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void playBlueAt(int column) {
		if (!blueTurn()) {
	        throw new RuntimeException( "It's not the player's turn." );
	    }
		
		if (column < 0 || column >= base || isColumnFull(column)) {
			player.setTurn(this);
	        throw new RuntimeException( "Invalid move. Your turn has been lost" );
	    }
		
		player.playAt(this, column);
        player.setTurn(this);
	}
	
	public boolean isColumnFull(int column) {
		for (int row = 0; row < height; row++) {
	        if (grid[row][column] == c) {
	            // If any cell in the column is equal to the fill character, the column is not full
	            return false;
	        }
	    }
		return true;
	}
	
	public boolean redTurn() {
		return turn == 'R';
	}
	
	public boolean blueTurn() {
		return turn == 'B';
	}	

    public char getTurn() {
		return turn;
	}
}
