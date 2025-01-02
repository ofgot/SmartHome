package sir.smarthome.reports;

import sir.smarthome.commands.Command;
import sir.smarthome.devices.Device;
import sir.smarthome.devices.TemperatureSensor;
import sir.smarthome.observers.Observer;
import sir.smarthome.residents.Resident;

import java.util.*;

public class ReportGenerator {
    private ReportStrategy reportStrategy;

    private List<Device> devices;
    private Map<Resident, Map<Device, Integer>> residentDeviceUsages = new HashMap<>();
    private List<Event> events = new ArrayList<>();

    public ReportGenerator(ReportStrategy reportStrategy) {
        this.reportStrategy = reportStrategy;
        devices = new ArrayList<>();
    }

    public void setReportStrategy(ReportStrategy reportStrategy) {
        this.reportStrategy = reportStrategy;
    }

    public void registerCommand(Command command) {
        Event event = new Event(command.toString(), new Date(), command);
        events.add(event);

        // change
        if (command.getReceiver() instanceof Device || command.getReceiver() instanceof Observer) {
            devices.add((Device) command.getReceiver());
        }

        if (command.getExecutor() instanceof Resident && command.getReceiver() instanceof Device) {
            if (residentDeviceUsages.containsKey((Resident) command.getExecutor())) {
                Map<Device, Integer> deviceUsages = residentDeviceUsages.get((Resident) command.getExecutor());

                if (deviceUsages.containsKey((Device) command.getReceiver())) {
                    deviceUsages.compute((Device) command.getReceiver(), (k, usages) -> usages + 1);
                } else {
                    deviceUsages.put((Device) command.getReceiver(), 1);
                }
            } else {
                HashMap<Device, Integer> deviceUsages = new HashMap<>();
                deviceUsages.put((Device) command.getReceiver(), 1);
                residentDeviceUsages.put((Resident) command.getExecutor(), deviceUsages);
            }
        }
    }

    public String generateReport() {
        if (reportStrategy instanceof ConsumptionReportStrategy) {
            ((ConsumptionReportStrategy) reportStrategy).setData(devices);
        } else if (reportStrategy instanceof ActivityReportStrategy) {
            ((ActivityReportStrategy) reportStrategy).setData(residentDeviceUsages);
        } else if (reportStrategy instanceof EventReportStrategy) {
            ((EventReportStrategy) reportStrategy).setData(events);
        }

        return reportStrategy.generateReport();
    }
}