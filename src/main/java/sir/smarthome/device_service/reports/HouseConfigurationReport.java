package sir.smarthome.device_service.reports;

import sir.smarthome.house_service.component.Building;

/**
 * Generates a configuration report for the house (building).
 * <p>
 * This report summarizes the configuration details of the house, such as its components and their statuses.
 */
public class HouseConfigurationReport {

    /**
     * The building for which the configuration report is generated.
     */
    private final Building building;

    /**
     * Constructs a new {@code HouseConfigurationReport} for the specified building.
     * <p>
     * The building parameter cannot be {@code null}, and an exception will be thrown if it is.
     *
     * @param building the {@link Building} for which the configuration report is generated
     * @throws IllegalArgumentException if the building is {@code null}
     */
    public HouseConfigurationReport(Building building) {
        if (building == null) {
            throw new IllegalArgumentException("Building cannot be null.");
        }
        this.building = building;
    }

    /**
     * Generates the configuration report for the house.
     * <p>
     * The report includes configuration details of the building, such as its components, based on the information
     * provided by the building's {@code appendReport} method.
     *
     * @return a string representing the formatted house configuration report
     */
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("\nHouse Configuration Report\n");
        report.append("==========================\n");

        // Append the building's configuration details to the report
        building.appendReport(report);

        report.append("==========================\n");
        return report.toString();
    }
}
