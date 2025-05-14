package sir.smarthome.device_service.factories;

import sir.smarthome.device_service.devices.Device;
import sir.smarthome.device_service.devices.Stove;

public class StoveFactory extends HouseholdAppliancesFactory{
    private static StoveFactory instance;

    private StoveFactory() {}

    public static StoveFactory getInstance() {
        if (instance == null) {
            instance = new StoveFactory();
        }
        return instance;
    }

    @Override
    public Device createDevice(double consumption, String name) {
        return new Stove(name,consumption);
    }
}
