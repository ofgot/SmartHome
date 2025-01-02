package sir.smarthome.reports;

import java.util.List;

public class EventReportStrategy implements ReportStrategy {
    private List<Event> data;
    
    public void setData(List<Event> data) {
        this.data = data;
    }
    
    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder("\nEvent Report:\n");
        report.append("==========================\n");
        for (Event event : data) {
            report.append(String.format("Date: %s, Action: %s%n",
                    event.getDate(), event.getAction()));
        }
        report.append("==========================");
        return report.toString();
    }
}