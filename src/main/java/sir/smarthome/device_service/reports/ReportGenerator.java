package sir.smarthome.device_service.reports;

import sir.smarthome.device_service.devices.Device;

import java.util.*;

/**
 * Generates reports based on different strategies ({@link ReportStrategy}).
 * <p>
 * The {@code ReportGenerator} class manages the collection of data needed to generate reports
 * related to device usage, events, or consumption, depending on the current strategy set.
 */
public class ReportGenerator {

    /**
     * The strategy used for generating reports.
     */
    private ReportStrategy reportStrategy;

    /**
     * List of devices in the system, used for generating consumption reports.
     */
    private List<Device> devices;

    /**
     * List of events in the system, used for generating event reports.
     */
    private List<Event> events = new ArrayList<>();

    /**
     * Constructs a new {@code ReportGenerator} with the specified report strategy.
     *
     * @param reportStrategy the initial strategy used to generate reports
     */
    public ReportGenerator(ReportStrategy reportStrategy) {
        this.reportStrategy = reportStrategy;
        devices = new ArrayList<>();
    }

    /**
     * Sets the report strategy for generating reports.
     *
     * @param reportStrategy the strategy to be used for generating the report
     */
    public void setReportStrategy(ReportStrategy reportStrategy) {
        this.reportStrategy = reportStrategy;
    }
}
