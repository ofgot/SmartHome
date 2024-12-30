package sir.smarthome.reports;

public class ReportGenerator {
    private ReportStrategy reportStrategy;

    public ReportGenerator(ReportStrategy reportStrategy) {
        this.reportStrategy = reportStrategy;
    }

    public void setReportStrategy(ReportStrategy reportStrategy) {
        this.reportStrategy = reportStrategy;
    }

    public String generateReport() {
        return reportStrategy.generateReport();
    }
}