package sir.smarthome.reports;

import sir.smarthome.house.component.Building;

public class HouseConfigurationReport {
    private final Building building;

    public HouseConfigurationReport(Building building) {
        if (building == null) {
            throw new IllegalArgumentException("Building cannot be null.");
        }
        this.building = building;
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("House Configuration Report\n");
        report.append("==========================\n");
        building.appendReport(report);
        return report.toString();
    }
}

