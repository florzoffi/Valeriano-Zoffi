package nemo0;

public class BelowOneState extends DepthState {
    public Object increaseDepth(Nemo nemo) {
        nemo.depth ++;
        nemo.depthStates.add( new BelowOneState() );
        return this;
    }

    public Object decreaseDepth(Nemo nemo) {
    	nemo.depth --;
        nemo.depthStates.remove( nemo.depthStates.size() - 1);
        return this;
    }
    
    public Object throwCapsule(Nemo nemo) {
    	throw new Error(Nemo.submarineHasExploded);
    }
}
