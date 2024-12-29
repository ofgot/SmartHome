package sir.smarthome.commands;

public class DeviceApi {
    private Command action;

    public DeviceApi() {}

    public void setAction(Command action) {
        this.action = action;
    }

    public void executeAction() {
        if (action != null) {
            action.execute();
        } else {
            System.out.println("No action set!");
        }
    }
}
