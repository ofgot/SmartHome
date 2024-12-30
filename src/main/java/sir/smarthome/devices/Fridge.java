package sir.smarthome.devices;

import sir.smarthome.common.Product;

import java.util.*;

public class Fridge implements Device {
    private UUID id;
    private String name;
    private double powerConsumption;
    private double usageDuration;
    private Date lastSync = new Date();
    private boolean isTurnedOn = true;
    private Date lastTurnOn = new Date();
    private int condition;

    public Fridge(String name, double powerConsumption) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.powerConsumption = powerConsumption;
        this.usageDuration = 0;
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
    
    public Product takeProduct(Product product) {
        System.out.println("Product " + product.getName() + " taken from fridge.");
        return product;
    }

    public void loadProduct(Product product) {
        System.out.println("Product " + product.getName() + " loaded into fridge.");
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
