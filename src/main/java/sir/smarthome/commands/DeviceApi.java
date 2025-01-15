package sir.smarthome.commands;

import sir.smarthome.reports.ReportGenerator;

public class DeviceApi {
    private Command action;
    private final ReportGenerator reportGenerator;

    public DeviceApi(ReportGenerator generator) {
        this.reportGenerator = generator;
    }

    public void setAction(Command action) {
        this.action = action;
    }

    /**
     * Executes the assigned action if it is set.
     * <p>
     * If an action is present, it registers the action with the report generator and executes it.
     * If no action is set, a message is printed to indicate this.
     */
    public void executeAction() {
        if (action != null) {
            reportGenerator.registerCommand(action);
            action.execute();
        } else {
            System.out.println("No action set!");
        }
    }
}
