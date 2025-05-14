package sir.smarthome.device_service.factories;


import sir.smarthome.device_service.devices.Device;

public abstract class DeviceFactory {
    public abstract Device createDevice(double consumption, String name);
}

