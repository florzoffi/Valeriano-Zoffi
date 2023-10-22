package nemo0;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nemo {
	private Direction direction;
    private Coordinates position;
    public int thrownCapsules;
    public int depth;
    public static String messageOfCapsule;
    public static String noCapsuleThrown = "No capsule has been thrown yet";
    public static String successfullLaunch = "There has been a successfull launch";
    public static String submarineHasExploded = "The submarine exploded";
    public List<DepthState> depthStates;
    private List<Command> commandList;


    public Nemo() {
    	direction = new RightDirection();
    	position = new Coordinates(0,0);
    	
    	commandList = Arrays.asList(
                new DepthDecreaseCommand(),
                new DepthIncreaseCommand(),
                new RotateLeftCommand(),
                new RotateRightCommand(),
                new ForwardCommand(),
                new ThrowCapsuleCommand()
            );
        
        messageOfCapsule = noCapsuleThrown;
        depth = 0;
        thrownCapsules = 0;
        
        depthStates = new ArrayList<DepthState>();
        depthStates.add( new SurfaceState() );
    }
    
    public boolean onSurface() {
    	return depth == 0;
        
    }

    public List<Integer> getPosition() {
        return position.getCoords();
    }

    public String getDirection() {
        return direction.getDirectionName();
    }

    public int getDepth() {
        return depth;
    }
    
    public String getMessage() {
    	return messageOfCapsule;
    }

    public Nemo action(String commands) {
        commands.chars()
            .mapToObj(ch -> (char) ch)
            .forEach(command -> commandList.stream()
                .filter(cmd -> cmd.matches(command))
                .forEach(cmd -> cmd.execute(this)));
        return this;
    }
    
    public Nemo throwCapsule() {
    	depthStates.get(depthStates.size() - 1).throwCapsule(this);
    	thrownCapsules++;
        return this;
    }

    public Nemo increaseDepth() {
    	depthStates.get(depthStates.size() - 1).increaseDepth(this);
        return this;
    }

    public Nemo decreaseDepth() {
    	depthStates.get(depthStates.size() - 1).decreaseDepth(this);
        return this;
    }

    public Nemo rotateLeft() {
        direction = direction.rotateLeft();
        return this;
    }

    public Nemo rotateRight() {
        direction = direction.rotateRight();
        return this;
    }

    public Nemo moveForward() {
    	position = position.moveForward(direction);
    	return this;
    }
}
