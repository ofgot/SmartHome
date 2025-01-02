package sir.smarthome.commands;

public interface Command<R,E> {
    public R getReceiver();
    public E getExecutor();
    public abstract void execute();
    public String toString();
}

