package nemo0;

import java.util.Arrays;

public class Direction {
    private String currentDirection;

    public Direction() {
        this.currentDirection = "right";
    }

    public String getCurrentDirection() {
        return currentDirection;
    }

    public void rotateLeft() {
        currentDirection = rotateDirection(1);
    }

    public void rotateRight() {
        currentDirection = rotateDirection(-1);
    }

    private String rotateDirection(int offset) {
        String[] directions = {"right", "up", "left", "down"};
        int currentIndex = Arrays.asList(directions).indexOf(currentDirection);
        int newIndex = (currentIndex + offset) % 4;

        if (newIndex < 0) {
            newIndex = 4 + newIndex;
        }

        return directions[newIndex];
    }
}
