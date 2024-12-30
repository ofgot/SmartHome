package sir.smarthome.reports;


import sir.smarthome.devices.Device;
import sir.smarthome.residents.Resident;

import java.util.HashMap;
import java.util.Map;

public class ActivityReportStrategy implements ReportStrategy {
    private final Map<Resident, Map<Device, Integer>> activityLog = new HashMap<>();

    public void logActivity(Resident resident, Device device) {
        activityLog
                .computeIfAbsent(resident, r -> new HashMap<>())
                .merge(device, 1, Integer::sum);
    }

    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder("Activity Report:\n");
        activityLog.forEach((resident, deviceMap) -> {
            report.append(String.format("Resident: %s%n", resident.getName()));
            deviceMap.forEach((device, count) ->
                    report.append(String.format("  Device: %s, Usage Count: %d%n", device.getName(), count))
            );
        });
        return report.toString();
    }
}

