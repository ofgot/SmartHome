package sir.smarthome.reports;

import sir.smarthome.devices.Device;
import sir.smarthome.residents.Resident;

import java.util.Map;

/**
 * Implementation of the {@link ReportStrategy} that generates an activity report.
 * <p>
 * This report summarizes the usage of devices by each resident, displaying the number of times each device was used.
 */
public class ActivityReportStrategy implements ReportStrategy {

    /**
     * Data containing the usage count of devices by each resident.
     * The map structure is: {@link Resident} -> {@link Device} -> Usage Count.
     */
    private Map<Resident, Map<Device, Integer>> data;

    /**
     * Sets the data for the activity report.
     *
     * @param data a map containing the usage data for each resident and their corresponding devices
     */
    public void setData(Map<Resident, Map<Device, Integer>> data) {
        this.data = data;
    }

    /**
     * Generates the activity report.
     * <p>
     * The report includes the name of each resident and the devices they have used, along with the corresponding usage count.
     *
     * @return a string representing the formatted activity report
     */
    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder("\nActivity Report:\n");
        report.append("====================\n");
        data.forEach((resident, deviceMap) -> {
            report.append(String.format("Resident: %s%n", resident.getName()));
            deviceMap.forEach((device, count) ->
                    report.append(String.format("  Device: %s, Usage Count: %d%n", device.getName(), count))
            );
        });
        report.append("====================");
        return report.toString();
    }
}
