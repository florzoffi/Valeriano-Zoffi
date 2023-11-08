package linea;

import java.util.ArrayList;

public class GameBoard {
    private ArrayList<ArrayList<Character>> grid;
    private int numRows;
    private int numColumns;
    private int[] columnHeights;

    public GameBoard(int height, int base) {
        if (base < 4 || height < 4) {
            throw new RuntimeException("To ensure every type of win is available, dimensions of four or above are required.");
        }

        this.numRows = height;
        this.numColumns = base;
        this.columnHeights = new int[numColumns];
        grid = new ArrayList<>(numColumns);

        for (int i = 0; i < numColumns; i++) {
            grid.add(new ArrayList<>(numRows)); // Initialize each column with empty lists
            columnHeights[i] = 0;
        }
    }

    public boolean isColumnFull(int column) {
        return columnHeights[column] >= numRows;
    }

    public boolean isCellEmpty(int row, int column) {
        return columnHeights[column] <= row;
    }

    public boolean placeChip(int column, char playerColor) {
        if (column < 0 || column >= numColumns || isColumnFull(column)) {
            throw new RuntimeException("Invalid move. Your turn has been lost");
        }

        grid.get(column).add(playerColor);
        columnHeights[column]++;
        return true; // Chip placed successfully.
    }

    public boolean checkHorizontalWin(char playerColor) {
        // Check for a horizontal win in rows with at least 4 chips.
        for (int row = 0; row < numRows; row++) {
            if (columnHeights[numColumns - 1] - columnHeights[0] < 4) {
                continue; // Skip rows with fewer than 4 chips in the columns.
            }

            for (int col = 0; col <= numColumns - 4; col++) {
                boolean isWin = true;
                for (int i = 0; i < 4; i++) {
                    if (grid.get(col + i).get(row) != playerColor) {
                        isWin = false;
                        break;
                    }
                }
                if (isWin) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkVerticalWin(char playerColor) {
        // Check for a vertical win.
        for (int col = 0; col < numColumns; col++) {
            if (columnHeights[col] - columnHeights[0] < 4) {
                continue; // Skip columns with fewer than 4 chips.
            }
            
            for (int row = 0; row <= numRows - 4; row++) {
                boolean isWin = true;
                for (int i = 0; i < 4; i++) {
                    if (grid.get(col).get(row + i) != playerColor) {
                        isWin = false;
                        break;
                    }
                }
                if (isWin) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkDiagonalWin(char playerColor, int row, int col) {
        return checkDiagonalWinDirection(playerColor, row, col, 1, 1) || // Check bottom-right direction
               checkDiagonalWinDirection(playerColor, row, col, 1, -1) || // Check top-right direction
               checkDiagonalWinDirection(playerColor, row, col, -1, 1) || // Check bottom-left direction
               checkDiagonalWinDirection(playerColor, row, col, -1, -1); // Check top-left direction
    }

    public boolean checkDiagonalWinDirection(char playerColor, int row, int col, int rowIncrement, int colIncrement) {
        int count = 1; // Initialize the count to 1 for the current chip

        // Check the direction specified by rowIncrement and colIncrement
        for (int i = 1; i < 4; i++) {
            int newRow = row + i * rowIncrement;
            int newCol = col + i * colIncrement;

            if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numColumns) {
                if (grid.get(newCol).get(newRow) == playerColor) {
                    count++;
                } else {
                    break; // If a different color chip is encountered, break the loop
                }
            } else {
                break; // If we go out of bounds, break the loop
            }
        }

        return count >= 4; // Return true if there are four or more chips in a row
    }

    
    public char[] getBoardAsCharArray() {
        char[] displayBoard = new char[(numColumns + 3) * (numRows + 2)];

        for (int col = 0; col < numColumns + 2; col++) {
            for (int row = 0; row < numRows + 2; row++) {
                if (col == 0 || col == numColumns + 1) {
                    // Vertical borders of the columns
                    displayBoard[row * (numColumns + 3) + col] = '|';
                } else if (row == 0 || row == numRows + 1) {
                    // Horizontal top and bottom borders
                    displayBoard[row * (numColumns + 3) + col] = '-';
                } else {
                    // Game cells
                    if (isCellEmpty(row - 1, col - 1)) {
                        displayBoard[row * (numColumns + 3) + col] = ' '; // Empty cell
                    } else {
                        char cellValue = grid.get(col - 1).get(row - 1);
                        displayBoard[row * (numColumns + 3) + col] = cellValue;
                    }
                }

                if (col == numColumns + 1) {
                    // Line break after the last column
                    displayBoard[row * (numColumns + 3) + col + 1] = '\n';
                }
            }
        }

        return displayBoard;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public boolean isBoardFull() {
        for (int column = 0; column < numColumns; column++) {
            if (!isColumnFull(column)) {
                return false; // If any column is not full, the board is not full.
            }
        }
        return true; // All columns are full, so the board is full.
    }

    public int getColumnHeight(int column) {
        if (column < 0 || column >= numColumns) {
            throw new IllegalArgumentException("Invalid column index");
        }
        
        return columnHeights[column];
    }
}
