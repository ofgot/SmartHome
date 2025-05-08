package sir.smarthome.house_service.component;

import java.util.ArrayList;
import java.util.List;

public class Floor implements HouseComponent {
    private final String name;
    private final List<HouseComponent> rooms = new ArrayList<>();

    public Floor(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addComponent(HouseComponent component) {
        rooms.add(component);
    }

    @Override
    public void removeComponent(HouseComponent component) {
        rooms.remove(component);
    }

    @Override
    public List<HouseComponent> getComponents() {
        return new ArrayList<>(rooms);
    }

    @Override
    public void appendReport(StringBuilder report) {
        report.append("==========================\n");
        report.append("Floor: ").append(name).append("\n");
        for (HouseComponent room : rooms) {
            room.appendReport(report);
        }
    }
}
