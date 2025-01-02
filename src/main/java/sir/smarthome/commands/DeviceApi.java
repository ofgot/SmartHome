package sir.smarthome.commands;

import sir.smarthome.devices.Device;
import sir.smarthome.reports.ReportGenerator;

import java.util.List;

public class DeviceApi {
    private Command action;
    private ReportGenerator reportGenerator;
    private List<Device> devices;

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
