package sir.smarthome.observers;

import sir.smarthome.common.Common;

import java.util.logging.Logger;

public class HeatingSystemObserver implements Observer {
    private static final Logger logger = Logger.getLogger(HeatingSystemObserver.class.getName());

    @Override
    public void update(int temperature) {
        if (temperature > Common.TEMPERATURE_BORDER_LINE) {
            turnOffHeatingSystem();
        } else {
            logger.info("Heating system is on.");
        }
    }

    public void turnOffHeatingSystem() {
        logger.info("Heating system turned off.");
    }

    @Override
    public String toString() {
        return "Heating System";
    }
}
