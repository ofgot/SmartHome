package sir.smarthome.house.component;

import java.util.ArrayList;
import java.util.List;

public class Building implements HouseComponent {
    private final String name;
    private final List<HouseComponent> floors = new ArrayList<>();

    public Building(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addComponent(HouseComponent component) {
        floors.add(component);
    }

    @Override
    public void removeComponent(HouseComponent component) {
        floors.remove(component);
    }

    @Override
    public List<HouseComponent> getComponents() {
        return new ArrayList<>(floors);
    }

    @Override
    public void appendReport(StringBuilder report) {
        report.append("Building: ").append(name).append("\n");
        for (HouseComponent floor : floors) {
            floor.appendReport(report);
        }
    }

    public record Information(String name, List<HouseComponent> floors) {};
    
    public Information getInformation() {
        return new Information(name, floors);
    }
}
