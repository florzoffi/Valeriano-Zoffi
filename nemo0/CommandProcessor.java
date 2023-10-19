package nemo0;

import java.util.Arrays;
import java.util.List;

public class CommandProcessor {
    private List<Command> commandList;
    
    public CommandProcessor() {
        commandList = Arrays.asList(
            new DepthDecreaseCommand(),
            new DepthIncreaseCommand(),
            new RotateLeftCommand(),
            new RotateRightCommand(),
            new ForwardCommand(),
            new ThrowCapsuleCommand()
        );
    }
    
    public void processCommands(String commands, Nemo<?> nemo) {
        commands.chars()
            .mapToObj(ch -> (char) ch)
            .forEach(command -> commandList.stream()
                .filter(cmd -> cmd.matches(command))
                .forEach(cmd -> cmd.execute(nemo)));
    }
    
    public abstract class Command {
        abstract boolean matches(char command);
        abstract void execute(Nemo<?> nemo);
    }

    public class DepthDecreaseCommand extends Command {
        @Override
        public boolean matches(char command) {
            return command == 'u';
        }

        @Override
        public void execute(Nemo<?> nemo) {
            nemo.decreaseDepth();
        }
    }

    public class DepthIncreaseCommand extends Command {
        @Override
        public boolean matches(char command) {
            return command == 'd';
        }

        @Override
        public void execute(Nemo<?> nemo) {
            nemo.increaseDepth();
        }
    }

    public class ForwardCommand extends Command {
        @Override
        public boolean matches(char command) {
            return command == 'f';
        }

        @Override
        public void execute(Nemo<?> nemo) {
            nemo.moveForward();
        }
    }

    public class RotateLeftCommand extends Command {
        @Override
        public boolean matches(char command) {
            return command == 'l';
        }

        @Override
        public void execute(Nemo<?> nemo) {
            nemo.rotateLeft();
        }
    }

    public class RotateRightCommand extends Command {
        @Override
        public boolean matches(char command) {
            return command == 'r';
        }

        @Override
        public void execute(Nemo<?> nemo) {
            nemo.rotateRight();
        }
    }

    public class ThrowCapsuleCommand extends Command {
        @Override
        public boolean matches(char command) {
            return command == 'm';
        }

        @Override
        public void execute(Nemo<?> nemo) {
            nemo.throwCapsule();
        }
    }
}
