package sir.smarthome.reports;

import sir.smarthome.devices.Device;

import java.util.HashMap;
import java.util.List;

/**
 * Implementation of the {@link ReportStrategy} that generates a consumption report.
 * <p>
 * This report summarizes the energy consumption of each device in the system.
 */
public class ConsumptionReportStrategy implements ReportStrategy {

    /**
     * Data containing the list of devices for which the consumption report will be generated.
     */
    private List<Device> data;

    /**
     * Sets the data for the consumption report.
     *
     * @param data a list of {@link Device} objects for which consumption data will be summarized
     */
    public void setData(List<Device> data) {
        this.data = data;
    }

    /**
     * Generates the consumption report.
     * <p>
     * The report includes the name of each device and its respective energy consumption in watt-hours (Wh).
     *
     * @return a string representing the formatted consumption report
     */
    @Override
    public String generateReport() {
        HashMap<Device, Double> consumptionList = new HashMap<>();

        // Populate consumption list with device and its usage consumption
        for (Device device : data) {
            consumptionList.put(device, device.getUsageConsumption());
        }

        StringBuilder report = new StringBuilder("\nConsumption Report:\n");
        report.append("====================\n");

        // Add each device and its consumption to the report
        consumptionList.forEach((device, consumption) ->
                report.append(String.format("Device: %s, Consumption: %.2f Wh%n", device.getName(), consumption))
        );
        report.append("====================");

        return report.toString();
    }
}
