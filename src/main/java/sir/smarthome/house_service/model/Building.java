package sir.smarthome.house_service.model;

import java.util.*;

public class Building  {
    private final UUID id;
    private final String name;
    private final List<Floor> floors = new ArrayList<>();

    public Building(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public List<Floor> getFloors() {
        return Collections.unmodifiableList(floors); //protected copy
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public void removeFloor(int floorNumber) {
        floors.removeIf(f -> f.getFloorNumber() == floorNumber);
    }

    public Floor findFloor(int floorNumber) {
        return floors.stream()
                .filter(f -> f.getFloorNumber() == floorNumber)
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("No floor " + floorNumber + " in building with id" + id));
    }
}
