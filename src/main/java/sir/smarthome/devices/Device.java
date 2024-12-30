package sir.smarthome.devices;

import java.util.*;

public interface Device {
    UUID getId();
    String getName();
    double getPowerConsumption();
    int getUsageDuration();
    Date getLastTurnOn();
    void turnOn();
    void turnOff();
    double getUsageConsumption();
    int getCondition();
}
