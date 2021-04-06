package za.co.wethinkcode.toyrobot;

public abstract class Command {
    private final String name;
    private final String arguments;

    public Command(String name, String arguments) {
        this.name = name.toLowerCase().trim();
        this.arguments = arguments.trim();
    }

    public Command(String name){
        this.name = name.toLowerCase().trim();
        this.arguments = "";
    }

    public String getName() {
        return this.name;
    }

    public String getArgument() {
        return this.arguments;
    }

    public abstract boolean execute(Robot target);

    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]){
            case "shutdown":
                return new ShutdownCommand();
            case "help":
                return new HelpCommand();
            case "forward":
                return new ForwardCommand(args[1]);
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }
}