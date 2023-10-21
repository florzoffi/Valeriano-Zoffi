package nemo0;

public class BelowOneState extends DepthState {
    public Object increaseDepth(Nemo nemo) {
        nemo.depth ++;
        return this;
    }

    public Object decreaseDepth(Nemo nemo) {
    	nemo.depth --;
    	if (nemo.getDepth() == 2) {
    	nemo.depthStates.add( new DepthOneState() );}
        return this;
    }
    
    public Object throwCapsule(Nemo nemo) {
    	throw new IllegalStateException(Nemo.submarineHasExploded);
    }
    
}
