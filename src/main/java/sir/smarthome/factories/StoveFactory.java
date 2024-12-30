package sir.smarthome.factories;

import sir.smarthome.devices.Device;
import sir.smarthome.devices.Stove;

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
