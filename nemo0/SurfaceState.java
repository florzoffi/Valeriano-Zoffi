package nemo0;

public class SurfaceState extends DepthState{
	
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