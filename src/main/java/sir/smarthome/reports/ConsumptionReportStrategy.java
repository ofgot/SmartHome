package sir.smarthome.reports;

import sir.smarthome.devices.Device;

import java.util.HashMap;
import java.util.List;

public class ConsumptionReportStrategy implements ReportStrategy {
    private List<Device> data;
    
    public void setData(List<Device> data) {
        this.data = data;
    }

    @Override
    public String generateReport() {
        HashMap<Device, Double> consumptionList = new HashMap<>();
        for (Device device : data) {
            consumptionList.put(device, device.getUsageConsumption());
        }

        StringBuilder report = new StringBuilder("\nConsumption Report:\n");
        report.append("====================\n");

        consumptionList.forEach((device, consumption) ->
                report.append(String.format("Device: %s, Consumption: %.2f Wh%n", device.getName(), consumption))
        );
        report.append("====================");

        return report.toString();
    }
}