package nemo0;

public class UpDirection extends Direction {
    @Override
    public String getDirectionName() {
        return "up";
    }

    @Override
    public Direction rotateLeft() {
        return new LeftDirection();
    }

    @Override
    public Direction rotateRight() {
        return new RightDirection();
    }

    @Override
    public Coordinates moveForward(Coordinates currentCoords) {
        return new Coordinates(currentCoords.getX(), currentCoords.getY() + 1);
    }
}