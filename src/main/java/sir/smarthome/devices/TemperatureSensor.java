package sir.smarthome.devices;

import sir.smarthome.observers.Observer;

import java.util.*;

public class TemperatureSensor extends Device {
    private int temperature;
    private List<Observer> observers;

    public TemperatureSensor(String name, double powerConsumption) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.powerConsumption = powerConsumption;
        this.usageDuration = 0;
        this.observers = new ArrayList<>();
        this.condition = 100;
    }

    public int getTemperature() {
        return temperature;
    }

    public List<Observer> getObservers() {
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
}
