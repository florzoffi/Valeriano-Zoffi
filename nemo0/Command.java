package nemo0;

public abstract class Command {
    abstract boolean matches(char command);
    abstract void execute(Nemo nemo);
}
