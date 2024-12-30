package sir.smarthome.factories;


import sir.smarthome.devices.Device;

public abstract class DeviceFactory {
    public abstract Device createDevice(double consumption);
}

