package sir.smarthome.house.component;

import org.junit.jupiter.api.Test;
import sir.smarthome.devices.Device;
import sir.smarthome.factories.ComputerFactory;
import sir.smarthome.residents.Human;

import java.util.UUID;


class RoomTest {

    @Test
    void testAddAndRemoveDevice() {
        Room room = new Room("Living Room");
        Device device = ComputerFactory.getInstance().createDevice();

        room.addDevice(device);

        room.removeDevice(device);
    }

    @Test
    void testAddAndRemoveResident() {
        Room room = new Room("Bedroom");
        Human john = new Human(UUID.randomUUID(), "John");

        room.addResident(john);

        room.removeResident(john);
    }

}