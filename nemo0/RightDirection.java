package nemo0;

public class RightDirection extends Direction {
    @Override
    public String getDirectionName() {
        return "right";
    }

    @Override
    public Direction rotateLeft() {
        return new UpDirection();
    }

    @Override
    public Direction rotateRight() {
        return new DownDirection();
    }

    @Override
    public Coordinates moveForward(Coordinates currentCoords) {
        return new Coordinates(currentCoords.getX() + 1, currentCoords.getY());
    }
}