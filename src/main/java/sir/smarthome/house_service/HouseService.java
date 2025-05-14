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
    Building updateBuilding(UUID buildingId, Building updatedBuilding) throws NoSuchElementException;
    void deleteBuilding(UUID buildingId);
    List<Building> listBuildings();

    // For floot
    Floor addFloor(UUID buildingId, Floor f) throws NoSuchElementException;
    Floor getFloor(UUID buildingId, int floorNumber) throws NoSuchElementException;
    void deleteFloor(UUID buildingId, int floorNumber) throws NoSuchElementException;
    List<Floor> getAllFloors(UUID buildingId) throws NoSuchElementException;

    // For room
    Room addRoom(UUID buildingId, int floorNumber, Room room) throws NoSuchElementException;
    Room getRoom(UUID buildingId, int floorNumber, UUID roomId) throws NoSuchElementException;
    void deleteRoom(UUID buildingId, int floorNumber, UUID roomId) throws NoSuchElementException;
    List<Room> listRooms(UUID buildingId, int floorNumber) throws NoSuchElementException;
    List<Room> listAllRooms(UUID buildingId) throws NoSuchElementException;
}
