package sir.smarthome.factories;

import sir.smarthome.devices.Device;
import sir.smarthome.devices.TemperatureSensor;

public class HeatingFactory extends DeviceFactory {
    private static HeatingFactory instance;

    private HeatingFactory() {}

    public static HeatingFactory getInstance() {
        if (instance == null) {
            instance = new HeatingFactory();
        }
        return instance;
    }

    @Override
    public Device createDevice() { // make inputs
        return new TemperatureSensor("Home Temperature Sensor", 50.0);
    }
}
