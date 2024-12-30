package sir.smarthome.factories;

import sir.smarthome.devices.Device;
import sir.smarthome.devices.TemperatureSensor;

public class SensorFactory extends DeviceFactory {
    private static SensorFactory instance;
    
    private SensorFactory() {}

    public static SensorFactory getInstance() {
        if (instance == null) {
            instance = new SensorFactory();
        }
        return instance;
    }

    @Override
    public Device createDevice(double consumption) { // make inputs
        return new TemperatureSensor("Home Temperature Sensor", 50.0);
    }
}
