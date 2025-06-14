package sir.smarthome.device_service.commands;

/**
 * Interface for command pattern.
 * Defines contract for executable commands.
 */
public interface Command<R> {
    public R getReceiver();
    public abstract void execute();
    public String toString();
}

