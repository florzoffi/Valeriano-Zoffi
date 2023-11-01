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
}