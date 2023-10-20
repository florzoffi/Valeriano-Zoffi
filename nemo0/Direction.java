package nemo0;

public abstract class Direction {
    public abstract String getDirectionName();
    public abstract Direction rotateLeft();
    public abstract Direction rotateRight();
    public abstract Coordinates moveForward(Coordinates currentCoords);
}

class UpDirection extends Direction {
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

class DownDirection extends Direction {
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

class LeftDirection extends Direction {
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

class RightDirection extends Direction {
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
