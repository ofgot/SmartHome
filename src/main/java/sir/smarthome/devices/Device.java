package sir.smarthome.devices;

import java.util.*;

public interface Device {
    UUID getId();
    String getName();
    double getPowerConsumption();
    double getUsageDuration();
    Date getLastTurnOn();
    void turnOn();
    void turnOff();
    void calculateUsageDuration();
    double getUsageConsumption();
    int getCondition();
}
