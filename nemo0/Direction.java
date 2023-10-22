package nemo0;

public abstract class Direction {
    public abstract String getDirectionName();
    public abstract Direction rotateLeft();
    public abstract Direction rotateRight();
    public abstract Coordinates moveForward(Coordinates currentCoords);
}