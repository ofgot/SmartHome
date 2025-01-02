package sir.smarthome.reports;


import sir.smarthome.devices.Device;
import sir.smarthome.residents.Resident;

import java.util.Map;

public class ActivityReportStrategy implements ReportStrategy {
    private Map<Resident, Map<Device, Integer>> data;

    public void setData(Map<Resident, Map<Device, Integer>> data) {
        this.data = data;
    }

    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder("Activity Report:\n");
        data.forEach((resident, deviceMap) -> {
            report.append(String.format("Resident: %s%n", resident.getName()));
            deviceMap.forEach((device, count) ->
                    report.append(String.format("  Device: %s, Usage Count: %d%n", device.getName(), count))
            );
        });
        return report.toString();
    }
}

