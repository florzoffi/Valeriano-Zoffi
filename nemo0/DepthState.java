package nemo0;

public abstract class DepthState {
    public abstract Object increaseDepth(Nemo nemo);
    public abstract Object decreaseDepth(Nemo nemo);
    public abstract Object throwCapsule(Nemo nemo);
}


	class BelowOneState extends DepthState {
    public Object increaseDepth(Nemo nemo) {
        nemo.depth ++;
        return this;
    }

    public Object decreaseDepth(Nemo nemo) {
    	nemo.depth --;
        nemo.depthStates.add( new DepthOneState() );
        return this;
    }
    
    public Object throwCapsule(Nemo nemo) {
    	throw new Error(Nemo.submarineHasExploded);
    }
}


	class DepthOneState extends DepthState{
    public Object increaseDepth(Nemo nemo) {
    	nemo.depth ++;
        nemo.depthStates.add( new BelowOneState() );
        return this;
    }

    public Object decreaseDepth(Nemo nemo) {
    	nemo.depth --;
        nemo.depthStates.add( new SurfaceState() );
        return this;
    }
    
    public Object throwCapsule(Nemo nemo) {
    	Nemo.messageOfCapsule = Nemo.successfullLaunch;
		return this;
    }
}


	class SurfaceState extends DepthState{
	@Override
    public Object increaseDepth(Nemo nemo) {
    	nemo.depth ++;
        nemo.depthStates.add(new DepthOneState());
        return this;
    }

	@Override
    public Object decreaseDepth(Nemo nemo) {
        nemo.depth = 0;
        return this;
    }

	@Override
	public Object throwCapsule(Nemo nemo) {
		Nemo.messageOfCapsule = Nemo.successfullLaunch;
		return this;
	}
}
