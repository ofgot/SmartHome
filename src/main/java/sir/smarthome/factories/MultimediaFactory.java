package sir.smarthome.factories;

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
    public Device createDevice() { //make inputs
        return new TV("Smart TV", 100);
    }
}
