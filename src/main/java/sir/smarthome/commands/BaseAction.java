package sir.smarthome.commands;

public abstract class BaseAction<R> implements Command {
    protected R receiver;

    public BaseAction(R receiver) {
        this.receiver = receiver;
    }

    public R getReceiver() {
        return receiver;
    }

    @Override
    public String toString() {
        return receiver.getClass().getName();
    }
}
