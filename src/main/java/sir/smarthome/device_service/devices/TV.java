package sir.smarthome.device_service.devices;

import java.util.*;
import java.util.logging.Logger;

public class TV extends Device {
    private static final Logger logger = Logger.getLogger(TV.class.getName());

    public TV(String name, double powerConsumption) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.powerConsumption = powerConsumption;
        this.usageDuration = 0;
        this.condition = 100;
    }

    public void changeChannel() {
        logger.info("Channel changed.");
    }

    public void increaseVolume(int increment) {
        logger.info("Volume increased by " + increment);
    }

    public void decreaseVolume(int decrement) {
        logger.info("Volume decreased by " + decrement);
    }
}