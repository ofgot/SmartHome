package sir.smarthome.house.component;

import java.util.ArrayList;
import java.util.List;

public class Floor implements HouseComponent {
    private String name;
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

    public record Information(String name, List<HouseComponent> rooms) {};

    public Information getInformation() {
        return new Information(name, rooms);
    }
}
