package sir.smarthome.factories;

import org.junit.jupiter.api.Test;
import sir.smarthome.devices.Computer;
import sir.smarthome.devices.Device;

import static org.junit.jupiter.api.Assertions.*;

class ComputerFactoryTest {

    @Test
    void getInstance() {
        DeviceFactory factory = ComputerFactory.getInstance();
        assertNotNull(factory, "Factory should not be null.");
        assertInstanceOf(DeviceFactory.class, factory, "Factory should be an instance of DeviceFactory.");
    }

    @Test
    void createDevice() {
        DeviceFactory factory = ComputerFactory.getInstance();
        Device device = factory.createDevice( 1, "Sensor");

        assertNotNull(device, "Device should not be null.");
        assertInstanceOf(Computer.class, device, "Device should be an instance of Computer.");
        assertEquals("Gaming Computer", device.getName(), "Device name should be 'Gaming Computer'.");
    }
}