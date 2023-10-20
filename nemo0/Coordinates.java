package nemo0;

import java.util.List;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Integer> getCoords() {
        return List.of(x, y);
    }

    public Coordinates moveForward(Direction direction) {
        return direction.moveForward(this);
    }
}