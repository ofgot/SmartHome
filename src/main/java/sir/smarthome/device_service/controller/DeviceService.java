package sir.smarthome.device_service.controller;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import sir.smarthome.common.DeviceEventDTO;
import sir.smarthome.common.Product;
import sir.smarthome.device_service.commands.*;
import sir.smarthome.device_service.devices.*;
import sir.smarthome.device_service.factories.*;
import sir.smarthome.device_service.kafka.SimpleKafkaProducer;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class DeviceService {
    private final Map<UUID, Device> devices = new HashMap<>();
    private final DeviceFactory computerFactory = ComputerFactory.getInstance();
    private final DeviceFactory fridgeFactory = FridgeFactory.getInstance();
    private final DeviceFactory multimediaFactory = MultimediaFactory.getInstance();
    private final DeviceFactory stoveFactory = StoveFactory.getInstance();
    private final DeviceApi deviceApi = new DeviceApi();
    private final SimpleKafkaProducer producer = new SimpleKafkaProducer();

    private final Cache<UUID, Device> deviceCache = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(100)
            .build();


    public DeviceService( ) {

    }

    public Device createDevice(String name, double power, String type, UUID roomId) {

        Device device;

        switch (type.toLowerCase()) {
            case "computer" -> device = computerFactory.createDevice(power, name);
            case "fridge" -> device = fridgeFactory.createDevice(power, name);
            case "tv" -> device = multimediaFactory.createDevice(power, name);
            case "stove" -> device = stoveFactory.createDevice(power, name);
            default -> throw new IllegalArgumentException("Imposter device: " + type);
        }
        DeviceEventDTO event = new DeviceEventDTO(
                device.getId(),
                device.getName(),
                device.getClass().getSimpleName(),
                "CREATED",
                roomId
        );
        producer.sendDeviceEvent(event);



        devices.put(device.getId(), device);
        return device;
    }

    public Map<UUID, Device> getDevices() {
        return devices;
    }

    // Common commands
    public void turnOnDevice(UUID id) {
        Device device = getDeviceById(id);
        if (device != null) {
            deviceApi.setAction(new TurnOnDeviceAction(device));
            deviceApi.executeAction();
        } else {
            System.err.println("Device not found: " + id);
        }
    }

    public void turnOffDevice(UUID id) {
        Device device = getDeviceById(id);
        if (device != null) {
            deviceApi.setAction(new TurnOffDeviceAction(device));
            deviceApi.executeAction();
        } else {
            System.err.println("Device not found: " + id);
        }
    }


    // TV command
    public void increaseVolume(UUID id, int step) {
        Device device = getDeviceById(id);
        if (device instanceof TV) {
            deviceApi.setAction(new IncreaseVolumeAction((TV) device, step));
            deviceApi.executeAction();
        } else {
            System.err.println("This device do not have this function: " + id);
        }
    }

    public void decreaseVolume(UUID id, int step) {
        Device device = getDeviceById(id);
        if (device instanceof TV) {
            deviceApi.setAction(new DecreaseVolumeAction((TV) device, step));
            deviceApi.executeAction();
        } else {
            System.err.println("This device do not have this function: " + id);
        }
    }

    // Fridge command
    public void loadFridge(UUID id, Product product) {
        Device device = getDeviceById(id);
        if (device instanceof Fridge) {
            deviceApi.setAction(new LoadProductAction((Fridge) device, product));
            deviceApi.executeAction();
        } else {
            System.err.println("Can't load product to this device: " + id);
        }
    }

    public Device getDeviceById(UUID id) {
        Device cached = deviceCache.getIfPresent(id);
        if (cached != null) {
            //System.out.println("💾 [CACHE HIT] Device " + id);
            return cached;
        }

        Device device = devices.get(id);
        if (device != null) {
            deviceCache.put(id, device);
            //System.out.println("📥 [CACHE PUT] Device " + id);
        }
        return device;
    }


    public SimpleKafkaProducer getProducer() {
        return producer;
    }

}
