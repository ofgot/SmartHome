package sir.smarthome.house_service;

import sir.smarthome.house_service.model.Building;
import sir.smarthome.house_service.model.Floor;
import sir.smarthome.house_service.model.Room;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

// operations
public interface HouseService {
    // For house
    Building createBuilding(Building b);
    Building getBuildingById(UUID buildingId) throws NoSuchElementException;
    List<Building> listBuildings();
    List<Room> listAllRooms(UUID buildingId) throws NoSuchElementException;
    Room getRoomById(UUID roomId);

}

