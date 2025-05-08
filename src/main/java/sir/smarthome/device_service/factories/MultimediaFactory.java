package sir.smarthome.device_service.factories;

import sir.smarthome.device_service.devices.Device;
import sir.smarthome.device_service.devices.TV;
import sir.smarthome.devices.*;

public class MultimediaFactory extends DeviceFactory {
    private static MultimediaFactory instance;
    
    private MultimediaFactory() {}

    public static MultimediaFactory getInstance() {
        if (instance == null) {
            instance = new MultimediaFactory();
        }
        return instance;
    }

    @Override
    public Device createDevice(double consumption, String name) { //make inputs
        return new TV(name, consumption);
    }
}
