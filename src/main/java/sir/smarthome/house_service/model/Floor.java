package sir.smarthome.house_service.model;

import java.util.*;

public class Floor  {
    private final String name;
    private final int floorNumber;
    private final List<Room> rooms = new ArrayList<>();

    public Floor(String name, int floorNumber) {
        this.name = name;
        this.floorNumber = floorNumber;
    }

    public String getName() { return name; }
    public int getFloorNumber() { return floorNumber; }
    public List<Room> getRooms() {
        return Collections.unmodifiableList(rooms); // protected copy

        }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(UUID roomId) {
        rooms.removeIf(r -> r.getId().equals(roomId));
    }

    public Room findRoom(UUID roomId) {
        return rooms.stream()
                .filter(r -> r.getId().equals(roomId))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("No room " + roomId + " on floor " + floorNumber));
    }

}
