package sir.smarthome.factories;

import sir.smarthome.devices.Device;
import sir.smarthome.devices.Fridge;

public class FridgeFactory extends HouseholdAppliancesFactory {
    private static FridgeFactory instance;
    
    private FridgeFactory() {}

    public static FridgeFactory getInstance() {
        if (instance == null) {
            instance = new FridgeFactory();
        }
        return instance;
    }

    @Override
    public Device createDevice() {
        return new Fridge("Double Door Fridge", 120.0);
    }
}

