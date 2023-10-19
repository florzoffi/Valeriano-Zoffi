package nemo0;

import java.util.ArrayList;
import java.util.Arrays;

public class Position {
    private ArrayList<Integer> coords;

    public Position() {
        coords = new ArrayList<>(Arrays.asList(0, 0));
    }

    public ArrayList<Integer> getCoords() {
        return coords;
    }

    public void moveForward(String direction) {
        int x = coords.get(0);
        int y = coords.get(1);

        if (direction.equals("right")) {
            x++;
        } else if (direction.equals("left")) {
            x--;
        } else if (direction.equals("up")) {
            y++;
        } else if (direction.equals("down")) {
            y--;
        }

        coords.set(0, x);
        coords.set(1, y);
    }
}
