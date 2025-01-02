package sir.smarthome.commands;

import sir.smarthome.devices.Device;
import sir.smarthome.reports.ReportGenerator;

import java.util.ArrayList;
import java.util.List;

public class DeviceApi {
    private Command action;
    private final ReportGenerator reportGenerator;
    private List<Device> devices;

    public DeviceApi(ReportGenerator generator) {
        this.reportGenerator = generator;
        devices = new ArrayList<>();
    }

    public void setAction(Command action) {
        this.action = action;
    }

    public void executeAction() {
        if (action != null) {
            reportGenerator.registerCommand(action);
            if (action.getReceiver() instanceof Device) {
                devices.add((Device) action.getReceiver());
            }
            action.execute();
        } else {
            System.out.println("No action set!");
        }
    }

    public List<Device> getDevices() {
        return devices;
    }
}
