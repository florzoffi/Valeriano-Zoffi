package nemo0;


import java.util.ArrayList;
import java.util.List;

public class Nemo {
	private Direction direction;
    private Position position;
    public int depth;
    public static String messageOfCapsule;
    private CommandProcessor commandProcessor;
    public static String noCapsuleThrown = "No capsule has been thrown yet";
    public static String successfullLaunch = "There has been a successfull launch";
    public static String submarineHasExploded = "The submarine exploded";
    public List<DepthState> depthStates;


    public Nemo() {
    	direction = new Direction();
        position = new Position();
        commandProcessor = new CommandProcessor();
        
        messageOfCapsule = noCapsuleThrown;
        depth = 0;
        
        depthStates = new ArrayList<DepthState>();
        depthStates.add( new SurfaceState() );
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

    public Nemo action(String commands) {
        commandProcessor.processCommands(commands, this);
        return this;
    }
    
    public void throwCapsule() {
    	DepthState currentDepthState = (DepthState) depthStates.get(depthStates.size() - 1);
        currentDepthState.throwCapsule(this);
    }

    public void increaseDepth() {
    	DepthState currentDepthState = (DepthState) depthStates.get(depthStates.size() - 1);
        currentDepthState.increaseDepth(this);
    }

    public void decreaseDepth() {
    	DepthState currentDepthState = (DepthState) depthStates.get(depthStates.size() - 1);
        currentDepthState.decreaseDepth(this);
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
