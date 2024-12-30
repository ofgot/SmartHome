package sir.smarthome.reports;

import java.util.ArrayList;
import java.util.List;

public class EventReportStrategy implements ReportStrategy {
    private final List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        events.add(event);
    }

    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder("Event Report:\n");
        for (Event event : events) {
            report.append(String.format("Date: %s, Action: %s, Resident & Device: %s%n",
                    event.getDate(), event.getAction(), event.getResidentAndDevice()));
        }
        return report.toString();
    }
}