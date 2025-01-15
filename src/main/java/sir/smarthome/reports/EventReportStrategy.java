package sir.smarthome.reports;

import java.util.List;

/**
 * Implementation of the {@link ReportStrategy} that generates an event report.
 * <p>
 * This report summarizes the events that occurred in the system, including the date and the action taken.
 */
public class EventReportStrategy implements ReportStrategy {

    /**
     * Data containing the list of events to be included in the report.
     */
    private List<Event> data;

    /**
     * Sets the data for the event report.
     *
     * @param data a list of {@link Event} objects representing the events to be reported
     */
    public void setData(List<Event> data) {
        this.data = data;
    }

    /**
     * Generates the event report.
     * <p>
     * The report includes the date and action for each event in the system.
     *
     * @return a string representing the formatted event report
     */
    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder("\nEvent Report:\n");
        report.append("==========================\n");

        // Loop through each event and add its details to the report
        for (Event event : data) {
            report.append(String.format("Date: %s, Action: %s%n",
                    event.getDate(), event.getAction()));
        }

        report.append("==========================");
        return report.toString();
    }
}
