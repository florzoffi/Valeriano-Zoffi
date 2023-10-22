package nemo0;

public class DownDirection extends Direction {
    @Override
    public String getDirectionName() {
        return "down";
    }

    @Override
    public Direction rotateLeft() {
        return new RightDirection();
    }

    @Override
    public Direction rotateRight() {
        return new LeftDirection();
    }

    @Override
    public Coordinates moveForward(Coordinates currentCoords) {
        return new Coordinates(currentCoords.getX(), currentCoords.getY() - 1);
    }
}
