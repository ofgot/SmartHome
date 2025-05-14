package sir.smarthome.device_service.devices;

import java.util.UUID;

public class Stove extends Device {
    public Stove(String name, double powerConsumption) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.powerConsumption = powerConsumption;
        this.usageDuration = 0;
        this.condition = 100;
    }
}
