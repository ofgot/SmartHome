package sir.smarthome.house_service.component;

import sir.smarthome.common.Equipment;
import sir.smarthome.device_service.devices.Device;

import java.util.ArrayList;
import java.util.List;

public class Room implements HouseComponent {
    private final String name;
    private final List<Device> devices = new ArrayList<>();
    private final List<Equipment> equipment = new ArrayList<>();

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
        if (!equipment.isEmpty()) {
            report.append("  Equipment:\n");
            for (Equipment equipment : equipment) {
                report.append("    - ").append(equipment.getName()).append("\n");
            }
        } else {
            report.append("  No equipment\n");
        }
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public void removeDevice(Device device) {
        devices.remove(device);
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void addEquipment(Equipment equipment) {
        this.equipment.add(equipment);
    }

    public void removeEquipment(Equipment equipment) {
        this.equipment.remove(equipment);
    }
}
