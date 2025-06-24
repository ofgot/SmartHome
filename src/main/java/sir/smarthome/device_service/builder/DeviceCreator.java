package sir.smarthome.device_service.builder;

import sir.smarthome.device_service.devices.Device;
import sir.smarthome.device_service.devices.DeviceType;
import sir.smarthome.device_service.factories.DeviceFactory;

/**
 * Responsible for creating instances of {@link Device} based on the provided type.
 * Delegates creation to the corresponding {@link DeviceFactory} depending on the device type.
 *
 * This class separates the concern of object creation from services that manage devices.
 */
public class DeviceCreator {
    private final DeviceFactory computerFactory;
    private final DeviceFactory fridgeFactory;
    private final DeviceFactory multimediaFactory;
    private final DeviceFactory stoveFactory;

    public DeviceCreator(
            DeviceFactory computerFactory,
            DeviceFactory fridgeFactory,
            DeviceFactory multimediaFactory,
            DeviceFactory stoveFactory
    ) {
        this.computerFactory = computerFactory;
        this.fridgeFactory = fridgeFactory;
        this.multimediaFactory = multimediaFactory;
        this.stoveFactory = stoveFactory;
    }

    public Device create(String name, double power, DeviceType type) {
        return switch (type) {
            case COMPUTER -> computerFactory.createDevice(power, name);
            case FRIDGE   -> fridgeFactory.createDevice(power, name);
            case TV       -> multimediaFactory.createDevice(power, name);
            case STOVE    -> stoveFactory.createDevice(power, name);
        };
    }

}


