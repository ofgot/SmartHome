package sir.smarthome.device_service.service;

import com.google.common.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sir.smarthome.DeviceRepository;
import sir.smarthome.common.DeviceEventDTO;
import sir.smarthome.common.Product;
import sir.smarthome.device_service.commands.*;
import sir.smarthome.device_service.devices.*;
import sir.smarthome.device_service.factories.*;
import sir.smarthome.device_service.kafka.SimpleKafkaProducer;
import sir.smarthome.elasticsearch.DeviceIndexer;

import java.util.*;

public class DeviceService {
    private static final Logger logger = LoggerFactory.getLogger(DeviceService.class);

    private final Map<UUID, Device> devices;
    private final DeviceFactory computerFactory;
    private final DeviceFactory fridgeFactory;
    private final DeviceFactory multimediaFactory;
    private final DeviceFactory stoveFactory;
    private final DeviceApi deviceApi;
    private final SimpleKafkaProducer producer;
    private final DeviceIndexer indexer;
    private final Cache<UUID, Device> deviceCache;

    public DeviceService(
            DeviceFactory computerFactory,
            DeviceFactory fridgeFactory,
            DeviceFactory multimediaFactory,
            DeviceFactory stoveFactory,
            DeviceApi deviceApi,
            SimpleKafkaProducer producer,
            DeviceIndexer indexer,
            Cache<UUID, Device> deviceCache,
            DeviceRepository deviceRepository
    ) {
        this.computerFactory = computerFactory;
        this.fridgeFactory = fridgeFactory;
        this.multimediaFactory = multimediaFactory;
        this.stoveFactory = stoveFactory;
        this.deviceApi = deviceApi;
        this.producer = producer;
        this.indexer = indexer;
        this.deviceCache = deviceCache;
        this.devices = deviceRepository.getDevices();
    }

    public Device createDevice(String name, double power, String type, UUID roomId) {
        Device device = switch (type.toLowerCase()) {
            case "computer" -> computerFactory.createDevice(power, name);
            case "fridge" -> fridgeFactory.createDevice(power, name);
            case "tv" -> multimediaFactory.createDevice(power, name);
            case "stove" -> stoveFactory.createDevice(power, name);
            default -> throw new IllegalArgumentException("Imposter device: " + type);
        };

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
            logger.warn("Device not found: {}", id);
        }
    }

    public void turnOffDevice(UUID id) {
        Device device = getDeviceById(id);
        if (device != null) {
            deviceApi.setAction(new TurnOffDeviceAction(device));
            deviceApi.executeAction();
        } else {
            logger.warn("Device not found: {}", id);
        }
    }


    // TV command
    public void increaseVolume(UUID id, int step) {
        Device device = getDeviceById(id);
        if (device instanceof TV) {
            deviceApi.setAction(new IncreaseVolumeAction((TV) device, step));
            deviceApi.executeAction();
        } else {
            logger.warn("This device does not support volume control: {}", id);
        }
    }

    public void decreaseVolume(UUID id, int step) {
        Device device = getDeviceById(id);
        if (device instanceof TV) {
            deviceApi.setAction(new DecreaseVolumeAction((TV) device, step));
            deviceApi.executeAction();
        } else {
            logger.warn("This device does not support volume control: {}", id);
        }
    }

    // Fridge command
    public void loadFridge(UUID id, Product product) {
        Device device = getDeviceById(id);
        if (device instanceof Fridge) {
            deviceApi.setAction(new LoadProductAction((Fridge) device, product));
            deviceApi.executeAction();
        } else {
            logger.warn("Can't load product to this device: {}", id);
        }
    }

    public Device getDeviceById(UUID id) {
        Device cached = deviceCache.getIfPresent(id);
        if (cached != null) {
//            logger.info("[CACHE HIT] Device {}", id);
            return cached;
        }

        Device device = devices.get(id);
        if (device != null) {
            deviceCache.put(id, device);
            indexer.index(device);
            //logger.info("[CACHE PUT] Device {}" + id);
        }
        return device;
    }


    public SimpleKafkaProducer getProducer() {
        return producer;
    }

}
