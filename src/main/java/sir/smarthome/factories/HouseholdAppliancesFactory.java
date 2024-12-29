package sir.smarthome.factories;

import sir.smarthome.devices.Device;

import java.lang.reflect.Type;

public abstract class HouseholdAppliancesFactory extends DeviceFactory {
    private static HouseholdAppliancesFactory instance;

    public abstract HouseholdAppliancesFactory getInstance();
    
    public Device createDevice(String type) {
        return switch (type) {
            case "fridge" -> {
                DeviceFactory factory = new FridgeFactory();
                yield factory.createDevice();
            }
            case "computer" -> {
                DeviceFactory factory1 = new ComputerFactory();
                yield factory1.createDevice();
            }
            default -> throw new RuntimeException("Unsupported device type: " + type);
        };
    }
}