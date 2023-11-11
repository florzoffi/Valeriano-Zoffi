package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class GameBoard {
    private ArrayList<ArrayList<Character>> grid;
    private int height;
    private int base;

    public GameBoard( int height, int base ) {
        this.height = height;
        this.base = base;
        grid = new ArrayList<>( base );
        
        IntStream.range( 0, base )
        .mapToObj( i -> new ArrayList<Character>() )
        .forEach( grid::add );
    }
    
    public boolean isBoardFull() {
        return grid.stream().allMatch( column -> column.size() == height );
    }

	public GameBoard placeChip( int givenColumn, char color ) {
		if ( givenColumn < 0 || givenColumn >= base || !spaceInColumnLeft( givenColumn ) ) {
            throw new RuntimeException( "This is an invalid move." );
        }

        ArrayList<Character> column = grid.get( givenColumn );
        column.add(color);
        return this;
	}
	
	public char insideCell( int givenRow, int givenColumn ) {
	    ArrayList<Character> column = grid.get(givenColumn);
	    return column.size() > givenRow ? column.get(givenRow) : ' ';
	}

	public boolean spaceInColumnLeft( int givenColumn ) {
		ArrayList<Character> column = grid.get(givenColumn);
		return column.size() < height;
	}
		
	public char[] getBoardAsCharArray() {
	    char[] displayBoard = new char[ ( base + 3 ) * ( height + 2 ) ];

	    for ( int row = height; row >= 0; row-- ) {
	        for ( int col = 0; col < base + 2; col++ ) {
	            if ( col == 0 || col == base + 1 ) {
	                // Vertical borders of the columns
	                displayBoard[ ( height - row ) * ( base + 3 ) + col ] = '|';
	            } else if ( row == 0 || row == height + 1 ) {
	                // Horizontal top and bottom borders
	                displayBoard[ ( height - row ) * ( base + 3 ) + col ] = '-';
	            } else {
	                // Game cells
	                char cellValue = ' ';
	                if ( col > 0 && col <= base ) {
	                    // Check if the cell is not empty in your game board
	                    char color = insideCell( row - 1, col - 1 );
	                    cellValue = ( color == 'R' || color == 'B' ) ? color : ' ';
	                }
	                displayBoard[ ( height - row ) * ( base + 3 ) + col ] = cellValue;
	            }

	            if ( col == base + 1 ) {
	                // Line break after the last column
	                displayBoard[ ( height - row ) * ( base + 3 ) + col + 1 ] = '\n';
	            }
	        }
	    }

	    return displayBoard;
	}


	public boolean verticalWinCheck( int lastColumnPlayed, char colorPlayed, Line game ) {
	    ArrayList<Character> column = grid.get( lastColumnPlayed );

	    long consecutiveCount = IntStream.range( 0, column.size() - 1 )
	            .takeWhile( i -> column.get( i ) == colorPlayed )
	            .count();

	    if ( consecutiveCount >= 3 ) {
	        game.gameStatus = new WinStatus( this, colorPlayed );
	        return true; // Vertical win found
	    }
	    return false;
	}
	
	public boolean horizontalWinCheck( int lastColumnPlayed, char colorPlayed, Line game ) {
	    ArrayList<Character> column = grid.get( lastColumnPlayed) ;
	    int consecutiveCount = 1;

	    int[] directions = { -1, 1 };

	    consecutiveCount += Arrays.stream( directions )
	            .flatMap( direction -> IntStream.iterate( lastColumnPlayed + direction, i -> i >= 0 && i < base, i -> i + direction ) )
	            .filter( i -> grid.get( i ).size() > column.size() - 1 && grid.get( i ).get( column.size() - 1 ) == colorPlayed )
	            .count();

	    if ( consecutiveCount >= 4 ) {
	        game.gameStatus = new WinStatus( this, colorPlayed );
	        return true;
	    }

	    return false;
	}

	
	public boolean diagonalBottomLeftToTopRightWinCheck( int lastColumnPlayed, char colorPlayed, Line game ) {
	    ArrayList<Character> column = grid.get( lastColumnPlayed );
	    int consecutiveCount = 1;

	    // Check both diagonal directions
	    int[][] directions = { {  -1, 1  }, {  1, -1  } }; // Bottom left to top right and top right to bottom left

	    for ( int[] direction : directions ) {
	        int dirX = direction[ 0 ];
	        int dirY = direction[ 1 ];

	        for ( int i = 1; i < 4; i++ ) {
	            int x = lastColumnPlayed + i * dirX;
	            int y = column.size() - 1 + i * dirY;

	            if ( x >= 0 && x < base && y >= 0 && y < height ) {
	                if ( grid.get(x).size() > y && grid.get(x).get(y) == colorPlayed ) {
	                    consecutiveCount++;
	                } else {
	                    break; // Break the loop if a different color is encountered or the column is not as tall
	                }
	            }
	        }
	    }

	    if ( consecutiveCount >= 4 ) {
	        game.gameStatus = new WinStatus( this, colorPlayed );
	        return true; // Diagonal win found
	    }

	    return false;
	}
	
	public boolean diagonalTopLeftToBottomRightWinCheck( int lastColumnPlayed, char colorPlayed, Line game ) {
	    ArrayList<Character> column = grid.get( lastColumnPlayed );
	    int consecutiveCount = 1;

	    // Check both diagonal directions
	    int[][] directions = { { -1, -1 }, { 1, 1 } }; // Top left to bottom right and bottom right to top left

	    for ( int[] direction : directions ) {
	        int dirX = direction[ 0 ];
	        int dirY = direction[ 1 ];

	        for ( int i = 1; i < 4; i++ ) {
	            int x = lastColumnPlayed + i * dirX;
	            int y = column.size() - 1 + i * dirY;

	            if ( x >= 0 && x < base && y >= 0 && y < height ) {
	                if (grid.get( x ).size() > y && grid.get( x ).get( y ) == colorPlayed ) {
	                    consecutiveCount++;
	                } else {
	                    break; // Break the loop if a different color is encountered or the column is not as tall
	                }
	            }
	        }
	    }

	    if ( consecutiveCount >= 4 ) {
	        game.gameStatus = new WinStatus( this, colorPlayed );
	        return true; // Diagonal win found
	    }

	    return false;
	}
	
	public boolean isDraw( Line game ) {
	    return isBoardFull() && ( game.gameStatus = new DrawStatus( this ) ) != null;
	}
}