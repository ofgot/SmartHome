package sir.smarthome.commands;

public abstract class BaseAction<R, E> implements Command {
    protected R receiver;
    protected E executor;

    public BaseAction(R receiver, E executor) {
        this.receiver = receiver;
        this.executor = executor;
    }

    public R getReceiver() {
        return receiver;
    }

    public E getExecutor() {
        return executor;
    }

    @Override
    public String toString() {
        return receiver.getClass().getName() + " by " + executor.getClass().getName();
    }
}
