package sir.smarthome.reports;

import sir.smarthome.devices.Device;

import java.util.HashMap;
import java.util.List;

public class ConsumptionReportStrategy implements ReportStrategy {
    private final HashMap<Device, Double> consumptionList = new HashMap<>();

    public void collectData(List<Device> devices) {
        for (Device device : devices) {
            consumptionList.put(device, device.getUsageConsumption());
        }
    }

    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder("Consumption Report:\n");
        consumptionList.forEach((device, consumption) ->
                report.append(String.format("Device: %s, Consumption: %.2f Wh%n", device.getName(), consumption))
        );
        return report.toString();
    }
}