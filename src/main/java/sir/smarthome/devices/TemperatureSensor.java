package sir.smarthome.devices;

import java.util.*;

public class TemperatureSensor implements Device {
    private UUID id;
    private String name;
    private double powerConsumption;
    private int usageDuration;
    private Date lastTurnOn;
    private int temperature;
    private List<Observer> observers;

    public TemperatureSensor(String name, double powerConsumption) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.powerConsumption = powerConsumption;
        this.usageDuration = 0;
        this.observers = new ArrayList<>();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPowerConsumption() {
        return powerConsumption;
    }

    @Override
    public int getUsageDuration() {
        return usageDuration;
    }

    @Override
    public Date getLastTurnOn() {
        return lastTurnOn;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

    @Override
    public void turnOn() {
        lastTurnOn = new Date();
    }

    @Override
    public void turnOff() {
        usageDuration += (int) ((new Date().getTime() - lastTurnOn.getTime()) / 1000);
    }

    @Override
    public double getUsageConsumption() {
        return usageDuration * powerConsumption;
    }
}
