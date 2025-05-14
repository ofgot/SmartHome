package sir.smarthome.house_service.dto;

import java.util.List;
import java.util.UUID;

public class FloorDto {
    int floorNumber;
    String name;
    List<RoomDto> rooms;
    UUID buildingId;
}
