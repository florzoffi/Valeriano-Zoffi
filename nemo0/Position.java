package nemo0;

public class Position {
    private Coordinates coords;
    
    public Position(Coordinates initialCoords, Direction initialDirection) {
        coords = initialCoords;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public Coordinates moveForward(Direction direction) {
        return direction.moveForward(coords);
    }

    public Coordinates getCurrentPosition() {
        return coords;
    }
}