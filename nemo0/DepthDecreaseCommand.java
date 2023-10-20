package nemo0;

public class DepthDecreaseCommand extends Command {
    @Override
    public boolean matches(char command) {
        return command == 'u';
    }

    @Override
    public void execute(Nemo nemo) {
        nemo.decreaseDepth();
    }
}
