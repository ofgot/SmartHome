package sir.smarthome.device_service.factories;

import sir.smarthome.device_service.devices.Device;
import sir.smarthome.device_service.devices.Fridge;

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
    public Device createDevice(double consumption, String name) {
        return new Fridge(name, consumption);
    }
}

