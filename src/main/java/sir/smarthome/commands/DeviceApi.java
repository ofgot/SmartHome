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

    public void executeAction() {
        if (action != null) {
            reportGenerator.registerCommand(action);
            action.execute();
        } else {
            System.out.println("No action set!");
        }
    }
}
