package nemo0;

public class DepthIncreaseCommand extends Command {
    @Override
    public boolean matches(char command) {
        return command == 'd';
    }

    @Override
    public void execute(Nemo nemo) {
        nemo.increaseDepth();
    }
}
