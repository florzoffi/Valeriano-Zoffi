package linea;

import java.util.ArrayList;

public class GameBoard {
    private ArrayList<ArrayList<Cell>> grid;
    private int numRows;
    private int numColumns;

    public GameBoard(int height, int base) {
        if (base < 4 || height < 4) {
            throw new RuntimeException("To ensure every type of win is available, dimensions of four or above are required.");
        }

        this.numRows = height;
        this.numColumns = base;
        grid = new ArrayList<>(numColumns);

        // Initialize the grid with Cell objects.
        for (int i = 0; i < numColumns; i++) {
            ArrayList<Cell> column = new ArrayList<>(numRows);
            for (int j = 0; j < numRows; j++) {
                column.add(new Cell());
            }
            grid.add(column);
        }
    }

    public boolean isColumnFull(int column) {
        // Check if a column is full.
        ArrayList<Cell> col = grid.get(column);
        for (Cell cell : col) {
            if (!cell.isOccupied()) {
                return false; // If any cell in the column is not occupied, the column is not full.
            }
        }
        return true; // All cells in the column are occupied, so the column is full.
    }

    public boolean isCellEmpty(int row, int column) {
        // Check if a specific cell is empty.
        return !grid.get(column).get(row).isOccupied();
    }

    public boolean placeChip(int column, char playerColor) {
        // Place a player's chip in a specific column.
        ArrayList<Cell> col = grid.get(column);
        for (int row = numRows - 1; row >= 0; row--) {
            if (!col.get(row).isOccupied()) {
                col.get(row).setPlayerColor(playerColor);
                return true; // Chip placed successfully.
            }
        }
        return false; // Column is full.
    }

    public void clearBoard() {
        // Clear the entire board.
        for (int i = 0; i < numColumns; i++) {
            ArrayList<Cell> col = grid.get(i);
            for (int j = 0; j < numRows; j++) {
                col.get(j).clear();
            }
        }
    }

    public boolean checkHorizontalWin(char playerColor) {
        // Check for a horizontal win.
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col <= numColumns - 4; col++) {
                boolean isWin = true;
                for (int i = 0; i < 4; i++) {
                    if (grid.get(col + i).get(row).getPlayerColor() != playerColor) {
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
            for (int row = 0; row <= numRows - 4; row++) {
                boolean isWin = true;
                for (int i = 0; i < 4; i++) {
                    if (grid.get(col).get(row + i).getPlayerColor() != playerColor) {
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

    public boolean checkDiagonalWin(char playerColor) {
        // Check for diagonal wins (both directions).
        return checkDiagonalWinBottomLeftToTopRight(playerColor) || checkDiagonalWinTopLeftToBottomRight(playerColor);
    }

    public boolean checkDiagonalWinBottomLeftToTopRight(char playerColor) {
        // Check for a diagonal win (bottom-left to top-right).
        for (int row = 0; row <= numRows - 4; row++) {
            for (int col = 0; col <= numColumns - 4; col++) {
                boolean isWin = true;
                for (int i = 0; i < 4; i++) {
                    if (grid.get(col + i).get(row + i).getPlayerColor() != playerColor) {
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

    public boolean checkDiagonalWinTopLeftToBottomRight(char playerColor) {
        // Check for a diagonal win (top-left to bottom-right).
        for (int row = 3; row < numRows; row++) {
            for (int col = 0; col <= numColumns - 4; col++) {
                boolean isWin = true;
                for (int i = 0; i < 4; i++) {
                    if (grid.get(col + i).get(row - i).getPlayerColor() != playerColor) {
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

    public boolean isBoardFull() {
        for (int column = 0; column < numColumns; column++) {
            if (!isColumnFull(column)) {
                return false; // If any column is not full, the board is not full.
            }
        }
        return true; // All columns are full, so the board is full.
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }
}