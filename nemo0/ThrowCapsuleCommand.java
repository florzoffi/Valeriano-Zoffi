package nemo0;

public class ThrowCapsuleCommand extends Command {
    @Override
    public boolean matches(char command) {
        return command == 'm';
    }

    @Override
    public void execute(Nemo nemo) {
        nemo.throwCapsule();
    }
}