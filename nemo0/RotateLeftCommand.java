package nemo0;


public class RotateLeftCommand extends Command {
    @Override
    public boolean matches(char command) {
        return command == 'l';
    }

    @Override
    public void execute(Nemo nemo) {
        nemo.rotateLeft();
    }
}