package sir.smarthome.device_service.controller;

import sir.smarthome.common.Product;
import sir.smarthome.device_service.commands.*;
import sir.smarthome.device_service.devices.*;
import sir.smarthome.device_service.factories.*;

import java.util.*;

public class DeviceService {
    private final Map<UUID, Device> devices = new HashMap<>();
    private final DeviceFactory computerFactory = ComputerFactory.getInstance();
    private final DeviceFactory fridgeFactory = FridgeFactory.getInstance();
    private final DeviceFactory multimediaFactory = MultimediaFactory.getInstance();
    private final DeviceFactory stoveFactory = StoveFactory.getInstance();
    private final DeviceApi deviceApi = new DeviceApi();

    public DeviceService( ) {

    }

    public Device createDevice(String name, double power, String type) {
        Device device;

        switch (type.toLowerCase()) {
            case "computer" -> device = computerFactory.createDevice(power, name);
            case "fridge" -> device = fridgeFactory.createDevice(power, name);
            case "tv" -> device = multimediaFactory.createDevice(power, name);
            case "stove" -> device = stoveFactory.createDevice(power, name);
            default -> throw new IllegalArgumentException("Imposter device: " + type);
        }

        devices.put(device.getId(), device);
        return device;
    }

    public Map<UUID, Device> getDevices() {
        return devices;
    }

    // Common commands
    public void turnOnDevice(UUID id) {
        Device device = devices.get(id);
        if (device != null) {
            deviceApi.setAction(new TurnOnDeviceAction(device));
            deviceApi.executeAction();
        } else {
            System.err.println("Device not found: " + id);
        }
    }

    public void turnOffDevice(UUID id) {
        Device device = devices.get(id);
        if (device != null) {
            deviceApi.setAction(new TurnOffDeviceAction(device));
            deviceApi.executeAction();
        } else {
            System.err.println("Device not found: " + id);
        }
    }


    // TV command
    public void increaseVolume(UUID id, int step) {
        Device device = devices.get(id);
        if (device instanceof TV) {
            deviceApi.setAction(new IncreaseVolumeAction((TV) device, step));
            deviceApi.executeAction();
        } else {
            System.err.println("This device do not have this function: " + id);
        }
    }

    public void decreaseVolume(UUID id, int step) {
        Device device = devices.get(id);
        if (device instanceof TV) {
            deviceApi.setAction(new DecreaseVolumeAction((TV) device, step));
            deviceApi.executeAction();
        } else {
            System.err.println("This device do not have this function: " + id);
        }
    }

    // Fridge command
    public void loadFridge(UUID id, Product product) {
        Device device = devices.get(id);
        if (device instanceof Fridge) {
            deviceApi.setAction(new LoadProductAction((Fridge) device, product));
            deviceApi.executeAction();
        } else {
            System.err.println("Can't load product to this device: " + id);
        }
    }

}
