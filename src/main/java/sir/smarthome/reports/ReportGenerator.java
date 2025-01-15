package sir.smarthome.reports;

import sir.smarthome.commands.Command;
import sir.smarthome.devices.Device;
import sir.smarthome.devices.TemperatureSensor;
import sir.smarthome.observers.Observer;
import sir.smarthome.residents.Resident;

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
     * Mapping of residents to their device usage, used for generating activity reports.
     */
    private Map<Resident, Map<Device, Integer>> residentDeviceUsages = new HashMap<>();

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

    /**
     * Registers a command and processes its effects on the data.
     * <p>
     * This method logs events related to the command execution and updates the relevant data structures
     * (such as devices and resident usage).
     *
     * @param command the command to register
     */
    public void registerCommand(Command command) {
        Event event = new Event(command.toString(), new Date(), command);
        events.add(event);

        // Add device to the report if the receiver is a device or observer
        if (command.getReceiver() instanceof Device || command.getReceiver() instanceof Observer) {
            devices.add((Device) command.getReceiver());
        }

        // Track device usage by resident if the command is executed on a device by a resident
        if (command.getExecutor() instanceof Resident && command.getReceiver() instanceof Device) {
            Resident executor = (Resident) command.getExecutor();
            Device receiver = (Device) command.getReceiver();

            residentDeviceUsages.computeIfAbsent(executor, Key -> new HashMap<>()).merge(receiver, 1, Integer::sum);
        }
    }

    /**
     * Generates the report based on the current strategy.
     * <p>
     * The report is generated according to the strategy set in {@link #setReportStrategy}.
     * It configures the relevant data and returns the formatted report string.
     *
     * @return a string representing the generated report
     */
    public String generateReport() {
        // Set the appropriate data for the chosen report strategy
        if (reportStrategy instanceof ConsumptionReportStrategy) {
            ((ConsumptionReportStrategy) reportStrategy).setData(devices);
        } else if (reportStrategy instanceof ActivityReportStrategy) {
            ((ActivityReportStrategy) reportStrategy).setData(residentDeviceUsages);
        } else if (reportStrategy instanceof EventReportStrategy) {
            ((EventReportStrategy) reportStrategy).setData(events);
        }

        return reportStrategy.generateReport();
    }
}
