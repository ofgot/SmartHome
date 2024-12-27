package sir.smarthome.factories;

import sir.smarthome.devices.Device;

public class HouseholdAppliancesFactory extends DeviceFactory {
    private static HouseholdAppliancesFactory instance;

    public static HouseholdAppliancesFactory getInstance() {
        if (instance == null) {
            instance = new HouseholdAppliancesFactory();
        }
        return instance;
    }

    @Override
    public Device createDevice() {
        throw new UnsupportedOperationException("Not supported yet."); // Make abstract factory
    }
}