package sir.smarthome.device_service.commands;

import java.util.logging.Logger;

public class DeviceApi {
    private static final Logger logger = Logger.getLogger(DeviceApi.class.getName());
    private Command action;

//    private final ReportGenerator reportGenerator;

//    public DeviceApi(ReportGenerator generator) {
//        this.reportGenerator = generator;
//    }

    public DeviceApi() {}

    public void setAction(Command action) {
        this.action = action;
    }

    /**
     * Executes the assigned action if it is set.
     * <p>
     * If an action is present, it registers the action with the report generator and executes it.
     * If no action is set, a message is printed to indicate this.
     */
    public void executeAction() {
        if (action != null) {
            action.execute();
        } else {
            logger.info("No action set!");
        }
    }
}
