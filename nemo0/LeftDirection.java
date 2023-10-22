package nemo0;

public class LeftDirection extends Direction {
    @Override
    public String getDirectionName() {
        return "left";
    }

    @Override
    public Direction rotateLeft() {
        return new DownDirection();
    }

    @Override
    public Direction rotateRight() {
        return new UpDirection();
    }

    @Override
    public Coordinates moveForward(Coordinates currentCoords) {
        return new Coordinates(currentCoords.getX() - 1, currentCoords.getY());
    }
}
