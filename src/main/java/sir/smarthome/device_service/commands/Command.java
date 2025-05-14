package sir.smarthome.device_service.commands;

public interface Command<R> {
    public R getReceiver();
    public abstract void execute();
    public String toString();
}

