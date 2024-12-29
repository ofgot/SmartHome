package sir.smarthome.common.command;

public abstract class Command<R, E> {
    private R receiver;
    private E executor;
    
    public abstract void execute();
    
    public String getName()
    {
        return "command";
    }
}
