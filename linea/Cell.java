package linea;

public class Cell {
    private char playerColor; // 'R' for red, 'B' for blue, or any other identifier
    private boolean occupied;

    public Cell() {
        playerColor = ' '; // Initialize as empty
        occupied = false;
    }

    public char getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(char playerColor) {
        this.playerColor = playerColor;
        occupied = true;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void clear() {
        playerColor = ' ';
        occupied = false;
    }
}