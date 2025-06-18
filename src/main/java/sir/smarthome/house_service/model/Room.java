package sir.smarthome.house_service.model;
import java.util.*;

public class Room  {
    private final UUID id;
    private final String name;
    private final UUID buildingId;
    private final int floorNumber;
    private final List<UUID> devices = new ArrayList<>();
    private UUID roomId;

    public Room(UUID id, String name, UUID buildingId, int floorNumber) {
        this.id = id;
        this.name = name;
        this.buildingId = buildingId;
        this.floorNumber = floorNumber;
        this.roomId = roomId;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<UUID> getDeviceIds() {
        return Collections.unmodifiableList(devices);
    }

    public void addDevice(UUID deviceId) {
        devices.add(Objects.requireNonNull(deviceId, "deviceId"));
    }


    public void removeDevice(UUID deviceId) {
        devices.remove(deviceId);
    }

    public Object getBuildingId() {
        return buildingId;
    }

    public Object getFloorNumber() {
        return floorNumber;
    }
}
