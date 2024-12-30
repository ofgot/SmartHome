package sir.smarthome.devices;

import java.util.Date;
import java.util.UUID;

public class Stove implements Device {
    private UUID id;
    private String name;
    private double powerConsumption;
    private int usageDuration;
    private Date lastTurnOn;
    private int condition;

    public Stove(String name, double powerConsumption) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.powerConsumption = powerConsumption;
        this.usageDuration = 0;
        this.condition = 100;
    }

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public double getPowerConsumption() {
        return 0;
    }

    @Override
    public int getUsageDuration() {
        return 0;
    }

    @Override
    public Date getLastTurnOn() {
        return null;
    }

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }

    @Override
    public void calculateUsageDuration() {
        
    }

    @Override
    public double getUsageConsumption() {
        return 0;
    }

    @Override
    public int getCondition() {
        return this.condition;
    }
}
