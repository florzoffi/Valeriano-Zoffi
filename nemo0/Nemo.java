package nemo0;


import java.util.ArrayList;

public class Nemo<E> {
	private Direction direction;
    private Position position;
    private int depth;
    private String messageOfCapsule;
    private CommandProcessor commandProcessor;
    public static String noCapsuleThrown = "No capsule has been thrown yet";
    public static String successfullLaunch = "There has been a successfull launch";
    public static String submarineHasExploded = "El submarino exploto";


    public Nemo() {
    	direction = new Direction();
        position = new Position();
        depth = 0;
        messageOfCapsule = noCapsuleThrown;
        commandProcessor = new CommandProcessor();
    }

    public boolean onSurface() {
        return depth == 0;
    }

    public ArrayList<Integer> getPosition() {
        return position.getCoords();
    }

    public String getDirection() {
        return direction.getCurrentDirection();
    }

    public int getDepth() {
        return depth;
    }
    
    public String getMessage() {
    	return messageOfCapsule;
    }

    public Object action(String commands) {
        commandProcessor.processCommands(commands, this);
        return this;
    }
    
    public void throwCapsule() {
        if (depth < 2) {
            messageOfCapsule = successfullLaunch;
        } else {
        	throw new IllegalStateException("The submarine has exploded");
        }
    }

    public void increaseDepth() {
        depth++;
    }

    public void decreaseDepth() {
        if (!onSurface()) {
            depth--;
        }
    }

    public void rotateLeft() {
        direction.rotateLeft();
    }

    public void rotateRight() {
        direction.rotateRight();
    }

    public void moveForward() {
    	position.moveForward(direction.getCurrentDirection());
    }
}
