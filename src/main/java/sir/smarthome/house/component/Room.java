package sir.smarthome.house.component;

import sir.smarthome.devices.Device;
import sir.smarthome.resident.Resident;

import java.util.ArrayList;
import java.util.List;

public class Room implements HouseComponent {
    private String name;
    private final List<Device> devices = new ArrayList<>();
    private final List<Resident> residents = new ArrayList<>();

    public Room(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addComponent(HouseComponent component) {
        throw new UnsupportedOperationException("Rooms do not contain other components.");
    }

    @Override
    public void removeComponent(HouseComponent component) {
        throw new UnsupportedOperationException("Rooms do not contain other components.");
    }

    @Override
    public List<HouseComponent> getComponents() {
        return List.of();
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public void removeDevice(Device device) {
        devices.remove(device);
    }

    public void addResident(Resident resident) {
        residents.add(resident);
    }

    public void removeResident(Resident resident) {
        residents.remove(resident);
    }

    public record Information(String name, List<Device> devices, List<Resident> residents) {};

    public Information getInformation() {
        return new Information(name, devices, residents);
    }
}
