package sir.smarthome.commands;

public interface Command {
    public Object getReceiver();
    public Object getExecutor();
    public abstract void execute();
    public String toString();
}

