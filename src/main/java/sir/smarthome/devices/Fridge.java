package sir.smarthome.devices;

import sir.smarthome.common.Product;

import java.util.*;

public class Fridge extends Device {
    public Fridge(String name, double powerConsumption) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.powerConsumption = powerConsumption;
        this.usageDuration = 0;
        this.condition = 100;
    }

    public Product takeProduct(Product product) {
        System.out.println("Product " + product.getName() + " taken from fridge.");
        return product;
    }

    public void loadProduct(Product product) {
        System.out.println("Product " + product.getName() + " loaded into fridge.");
    }
}
