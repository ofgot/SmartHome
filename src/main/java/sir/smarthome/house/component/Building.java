package sir.smarthome.house.component;

import java.util.ArrayList;
import java.util.List;

public class Building implements HouseComponent {
    private String name;
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
    
    public record Information(String name, List<HouseComponent> floors) {};
    
    public Information getInformation() {
        return new Information(name, floors);
    }
}
