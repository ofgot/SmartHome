package sir.smarthome.devices;

import sir.smarthome.observers.Observer;

import java.util.*;

public class TemperatureSensor implements Device {
    private UUID id;
    private String name;
    private double powerConsumption;
    private double usageDuration;
    private Date lastSync = new Date();
    private boolean isTurnedOn = true;
    private Date lastTurnOn = new Date();
    private int temperature;
    private List<Observer> observers;
    private int condition;

    public TemperatureSensor(String name, double powerConsumption) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.powerConsumption = powerConsumption;
        this.usageDuration = 0;
        this.observers = new ArrayList<>();
        this.condition = 100;
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
    public double getUsageDuration() {
        return usageDuration;
    }

    @Override
    public Date getLastTurnOn() {
        return lastTurnOn;
    }
    
    public int getTemperature()
    {
        return temperature;
    }
    
    public List<Observer> getObservers()
    {
        return observers;
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
        isTurnedOn = true;
        lastTurnOn = new Date();
    }

    @Override
    public void turnOff() {
        isTurnedOn = false;
    }

    @Override
    public void calculateUsageDuration() {
        if (!isTurnedOn) return;
        usageDuration += ((double) (new Date().getTime() - lastSync.getTime()) / 3600);
        lastSync = new Date();
    }

    @Override
    public double getUsageConsumption() {
        calculateUsageDuration();
        return usageDuration * powerConsumption;
    }
    
    @Override
    public int getCondition() {
        return this.condition;
    }
}
