package linea;

import java.util.ArrayList;

public class GameBoard {
    private ArrayList<ArrayList<Character>> grid;
    private int height;
    private int base;

    public GameBoard(int height, int base) {
        this.height = height;
        this.base = base;
        grid = new ArrayList<>(base);
        
        for (int i = 0; i < base; i++) {
            ArrayList<Character> column = new ArrayList<>();
            grid.add(column);
        }
    }

	public boolean isBoardFull() {
		for (ArrayList<Character> column : grid) {
			if (column.size() != height) {
                return false;
			}
		}
		return true;
	}

	public GameBoard placeChip(int givenColumn, char color) {
		if (givenColumn < 0 || givenColumn >= base || !spaceInColumnLeft(givenColumn)) {
            throw new RuntimeException("This is an invalid move.");
        }

        ArrayList<Character> column = grid.get(givenColumn);
        column.add(color);
        return this;
	}

	public char insideCell(int givenRow, int givenColumn) {
		ArrayList<Character> column = grid.get(givenColumn);
		return column.get(givenRow);
	}

	public boolean spaceInColumnLeft(int givenColumn) {
		ArrayList<Character> column = grid.get(givenColumn);
		return column.size() < height;
	}

	public char[] getBoardAsCharArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean verticalWinCheck(int lastColumnPlayed, char colorPlayed, Line game) {
		ArrayList<Character> column = grid.get(lastColumnPlayed);
		int consecutiveCount = 1;
		
		for (int i = column.size() - 2; i >= 0; i--) {
	        if (column.get(i) == colorPlayed) {
	            consecutiveCount++;
	            if (consecutiveCount == 4) {
	            	game.gameStatus = new FinishedStatus(this);
	                return true; // Vertical win found
	            }
	        } else {
	            break; // Break the loop if a different color is encountered
	        }
	    }

	    return false;
	}
	
	public boolean horizontalWinCheck(int lastColumnPlayed, char colorPlayed ) {
		// TODO 
		return false;
	}
	
	
}