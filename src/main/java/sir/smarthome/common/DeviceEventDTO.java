package sir.smarthome.common;

import java.util.UUID;

public class DeviceEventDTO {
    private UUID id;
    private String name;
    private String type;
    private String status;
    private UUID roomId;

    public DeviceEventDTO() {}

    public DeviceEventDTO(UUID id, String name, String type, String status, UUID roomId)  {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
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

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
