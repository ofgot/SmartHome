package sir.smarthome.house.component;

import sir.smarthome.devices.Device;
import sir.smarthome.residents.Resident;

import java.util.ArrayList;
import java.util.List;

public class Room implements HouseComponent {
    private final String name;
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

    @Override
    public void appendReport(StringBuilder report) {
        report.append("Room: ").append(name).append("\n");
        if (!devices.isEmpty()) {
            report.append("  Devices:\n");
            for (Device device : devices) {
                report.append("    - ").append(device.getName()).append("\n");
            }
        } else {
            report.append("  No devices\n");
        }
        if (!residents.isEmpty()) {
            report.append("  Residents:\n");
            for (Resident resident : residents) {
                report.append("    - ").append(resident.getName()).append("\n");
            }
        } else {
            report.append("  No residents\n");
        }
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
}
