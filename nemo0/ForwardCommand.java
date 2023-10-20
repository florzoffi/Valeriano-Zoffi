package nemo0;

public class ForwardCommand extends Command {
    @Override
    public boolean matches(char command) {
        return command == 'f';
    }

    @Override
    public void execute(Nemo nemo) {
        nemo.moveForward();
    }
}