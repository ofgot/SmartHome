package sir.smarthome.device_service.service;

import com.google.common.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sir.smarthome.DeviceRepository;
import sir.smarthome.common.DeviceEventDTO;
import sir.smarthome.common.Product;
import sir.smarthome.device_service.commands.*;
import sir.smarthome.device_service.devices.*;
import sir.smarthome.device_service.kafka.SimpleKafkaProducer;
import sir.smarthome.elasticsearch.DeviceIndexer;

import java.util.*;

/**
 * Core service for managing smart home devices.
 * Handles device registration, operations (on/off, volume, etc.),
 * caching, indexing, and event publishing.
 *
 * This class does not create devices directly, but registers already created ones
 * and allows interaction with them via predefined actions.
 */
public class DeviceService {
    private static final Logger logger = LoggerFactory.getLogger(DeviceService.class);

    private final Map<UUID, Device> devices;
    private final DeviceApi deviceApi;
    private final SimpleKafkaProducer producer;
    private final DeviceIndexer indexer;
    private final Cache<UUID, Device> deviceCache;

    /**
     * Constructs a {@code DeviceService} with the necessary dependencies.
     *
     * @param deviceApi        API used to execute actions on devices
     * @param producer         Kafka producer for sending device events
     * @param indexer          Service for indexing devices (e.g., in Elasticsearch)
     * @param deviceCache      Cache for fast device lookup
     * @param deviceRepository Repository providing the initial device map
     */
    public DeviceService(
            DeviceApi deviceApi,
            SimpleKafkaProducer producer,
            DeviceIndexer indexer,
            Cache<UUID, Device> deviceCache,
            DeviceRepository deviceRepository
    ) {
        this.deviceApi   = deviceApi;
        this.producer    = producer;
        this.indexer     = indexer;
        this.deviceCache = deviceCache;
        this.devices     = deviceRepository.getDevices();
    }

    /**
     * Registers a new device into the system, sends a creation event,
     * and stores it in the internal device map.
     *
     * @param device the newly created device
     * @param roomId the ID of the room the device belongs to
     */
    public void registerDevice(Device device, UUID roomId) {
        DeviceEventDTO event = new DeviceEventDTO(
                device.getId(),
                device.getName(),
                device.getClass().getSimpleName(),
                "CREATED",
                roomId
        );
        producer.sendDeviceEvent(event);
        devices.put(device.getId(), device);
    }

    /**
     * Returns the map of all managed devices.
     *
     * @return a map of devices keyed by their UUID
     */
    public Map<UUID, Device> getDevices() {
        return devices;
    }

    /**
     * Turns on a device by its ID, if found.
     *
     * @param id the UUID of the device
     */
    public void turnOnDevice(UUID id) {
        Device device = getDeviceById(id);
        if (device != null) {
            deviceApi.setAction(new TurnOnDeviceAction(device));
            deviceApi.executeAction();
        } else {
            logger.warn("Device not found: {}", id);
        }
    }

    /**
     * Turns off a device by its ID, if found.
     *
     * @param id the UUID of the device
     */
    public void turnOffDevice(UUID id) {
        Device device = getDeviceById(id);
        if (device != null) {
            deviceApi.setAction(new TurnOffDeviceAction(device));
            deviceApi.executeAction();
        } else {
            logger.warn("Device not found: {}", id);
        }
    }

    /**
     * Increases the volume of a device if it's a TV.
     *
     * @param id   the UUID of the device
     * @param step the amount to increase the volume by
     */
    public void increaseVolume(UUID id, int step) {
        Device device = getDeviceById(id);
        if (device instanceof TV) {
            deviceApi.setAction(new IncreaseVolumeAction((TV) device, step));
            deviceApi.executeAction();
        } else {
            logger.warn("This device does not support volume control: {}", id);
        }
    }

    /**
     * Decreases the volume of a device if it's a TV.
     *
     * @param id   the UUID of the device
     * @param step the amount to decrease the volume by
     */
    public void decreaseVolume(UUID id, int step) {
        Device device = getDeviceById(id);
        if (device instanceof TV) {
            deviceApi.setAction(new DecreaseVolumeAction((TV) device, step));
            deviceApi.executeAction();
        } else {
            logger.warn("This device does not support volume control: {}", id);
        }
    }

    /**
     * Loads a product into a device if it's a fridge.
     *
     * @param id      the UUID of the device
     * @param product the product to be loaded
     */
    public void loadFridge(UUID id, Product product) {
        Device device = getDeviceById(id);
        if (device instanceof Fridge) {
            deviceApi.setAction(new LoadProductAction((Fridge) device, product));
            deviceApi.executeAction();
        } else {
            logger.warn("Can't load product to this device: {}", id);
        }
    }

    /**
     * Retrieves a device by its ID, checking the cache first.
     * If not cached, fetches from internal storage and caches it.
     *
     * @param id the UUID of the device
     * @return the corresponding device, or {@code null} if not found
     */
    public Device getDeviceById(UUID id) {
        Device cached = deviceCache.getIfPresent(id);
        if (cached != null) {
            return cached;
        }

        Device device = devices.get(id);
        if (device != null) {
            deviceCache.put(id, device);
            indexer.index(device);
        }
        return device;
    }

    /**
     * Returns the Kafka producer used to publish device events.
     *
     * @return the Kafka producer instance
     */
    public SimpleKafkaProducer getProducer() {
        return producer;
    }

}
