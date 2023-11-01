package linea;

public class Coordinate {
    private int x;
    private int y;
    private char value;
    private Line line;

    public Coordinate(int x, int y, char value, Line line) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.line = line;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value == line.c;
    }
    
    public Coordinate findEmptyCoordinate(Line game, int column) {
        for (int y = game.height - 1; y >= 0; y--) {
            Coordinate coordinate = game.grid.get(y).get(column);
            if (coordinate.isEmpty()) {
                return coordinate;
            }
        }
        return null; // No empty cell found
    }
}