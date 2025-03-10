package sir.smarthome.devices;

import java.util.*;

public abstract class Device {
    protected UUID id;
    protected String name;
    protected double powerConsumption;
    protected double usageDuration;
    protected Date lastSync = new Date();
    protected boolean isTurnedOn = true;
    protected Date lastTurnOn = new Date();
    protected int condition;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPowerConsumption() {
        return powerConsumption;
    }

    public double getUsageDuration() {
        return usageDuration;
    }

    public Date getLastTurnOn() {
        return lastTurnOn;
    }

    public void turnOn() {
        isTurnedOn = true;
        lastTurnOn = new Date();
    }

    public void turnOff() {
        isTurnedOn = false;
    }

    public void calculateUsageDuration() {
        if (!isTurnedOn) return;
        usageDuration += ((double) (new Date().getTime() - lastSync.getTime()) / 3600);
        lastSync = new Date();
    }

    public double getUsageConsumption() {
        calculateUsageDuration();
        return usageDuration * powerConsumption;
    }

    public int getCondition() {
        return this.condition;
    }
}
