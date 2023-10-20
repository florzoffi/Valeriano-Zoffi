package nemo0;

public class RotateRightCommand extends Command {
    @Override
    public boolean matches(char command) {
        return command == 'r';
    }

    @Override
    public void execute(Nemo nemo) {
        nemo.rotateRight();
    }
}