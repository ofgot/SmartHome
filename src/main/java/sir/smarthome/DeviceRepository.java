package sir.smarthome;

import sir.smarthome.device_service.devices.Device;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Repository for device storage.
 * Provides access to device data in memory.
 */
public class DeviceRepository {
    private final Map<UUID, Device> devices = new HashMap<>();

    public Map<UUID, Device> getDevices() {
        return devices;
    }
}
