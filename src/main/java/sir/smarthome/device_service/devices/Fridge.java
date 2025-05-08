package sir.smarthome.device_service.devices;

import sir.smarthome.common.Product;

import java.util.*;
import java.util.logging.Logger;

public class Fridge extends Device {
    private static final Logger logger = Logger.getLogger(Fridge.class.getName());

    public Fridge(String name, double powerConsumption) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.powerConsumption = powerConsumption;
        this.usageDuration = 0;
        this.condition = 100;
    }

    public Product takeProduct(Product product) {
        logger.info("Product " + product.getName() + " taken from fridge.");
        return product;
    }

    public void loadProduct(Product product) {
        logger.info("Product " + product.getName() + " loaded into fridge.");
    }
}
