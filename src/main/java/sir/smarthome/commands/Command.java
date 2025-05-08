package sir.smarthome.commands;

public interface Command<R> {
    public R getReceiver();
    public abstract void execute();
    public String toString();
}

