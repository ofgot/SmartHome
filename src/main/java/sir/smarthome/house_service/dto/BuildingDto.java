package sir.smarthome.house_service.dto;

import java.util.List;
import java.util.UUID;

public class BuildingDto {
    UUID id;
    String name;
    List<FloorDto> floors;
}
