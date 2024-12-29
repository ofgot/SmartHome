package sir.smarthome.factories;

import sir.smarthome.devices.Computer;
import sir.smarthome.devices.Device;

public class ComputerFactory extends HouseholdAppliancesFactory {
    private static ComputerFactory instance;
    
    private ComputerFactory() {}

    public static ComputerFactory getInstance() {
        if (instance == null) {
            instance = new ComputerFactory();
        }
        return instance;
    }

    @Override
    public Device createDevice() { // make inputs
        return new Computer("Gaming Computer", 200.0);
    }
}

