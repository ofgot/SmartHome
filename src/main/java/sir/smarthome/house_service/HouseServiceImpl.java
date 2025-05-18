package sir.smarthome.house_service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import sir.smarthome.house_service.dao.BuildingDao;
import sir.smarthome.house_service.dao.BuildingDaoImpl;
import sir.smarthome.house_service.model.Building;
import sir.smarthome.house_service.model.Floor;
import sir.smarthome.house_service.model.Room;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HouseServiceImpl implements HouseService {
    private final BuildingDao dao = new BuildingDaoImpl();
    private final Cache<UUID, Room> roomCache = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(100)
            .build();

    @Override
    public Building createBuilding(Building b) {
        dao.saveBuilding(b);
        return b;
    }

    @Override
    public Building getBuildingById(UUID buildingId) {
        return dao.findBuildingById(buildingId)
                .orElseThrow(() ->
                        new NoSuchElementException("Building with id " + buildingId + "not found"));
    }

    @Override
    public Building updateBuilding(UUID buildingId, Building updatedBuilding) {
        Building existing = getBuildingById(buildingId);
        dao.saveBuilding(updatedBuilding);
        return updatedBuilding;
    }

    @Override
    public void deleteBuilding(UUID buildingId) {
        dao.deleteBuilding(buildingId);
    }

    @Override
    public List<Building> listBuildings() {
        return dao.findAll();
    }

    @Override
    public Floor addFloor(UUID buildingId, Floor f) {
        Building b = getBuildingById(buildingId);
        b.addFloor(f);
        dao.saveBuilding(b);
        return f;
    }

    @Override
    public Floor getFloor(UUID buildingId, int floorNumber) {
        Building b = getBuildingById(buildingId);
        return b.findFloor(floorNumber);
    }

    @Override
    public void deleteFloor(UUID buildingId, int floorNumber) {
        Building b = getBuildingById(buildingId);
        b.removeFloor(floorNumber);
        dao.saveBuilding(b);
    }

    @Override
    public List<Floor> getAllFloors(UUID buildingId) {
        return new ArrayList<>( getBuildingById(buildingId).getFloors() );
    }

    @Override
    public Room addRoom(UUID buildingId, int floorNumber, Room r) {
        Building b = getBuildingById(buildingId);
        Floor f = b.findFloor(floorNumber);
        f.addRoom(r);
        dao.saveBuilding(b);
        return r;
    }

    @Override
    public Room getRoom(UUID buildingId, int floorNumber, UUID roomId) {
        Building b = getBuildingById(buildingId);
        return b.findFloor(floorNumber)
                .findRoom(roomId);
    }

    @Override
    public void deleteRoom(UUID buildingId, int floorNumber, UUID roomId) {
        Building b = getBuildingById(buildingId);
        Floor f = b.findFloor(floorNumber);
        f.removeRoom(roomId);
        dao.saveBuilding(b);
    }

    @Override
    public List<Room> listRooms(UUID buildingId, int floorNumber) {
        return new ArrayList<>(
                getBuildingById(buildingId)
                        .findFloor(floorNumber)
                        .getRooms()
        );
    }

    @Override
    public List<Room> listAllRooms(UUID buildingId) {
        return getBuildingById(buildingId)
                .getFloors().stream()
                .flatMap(fl -> fl.getRooms().stream())
                .collect(Collectors.toList());
    }

    public Room getRoomById(UUID roomId) {
        Room cached = roomCache.getIfPresent(roomId);
        if (cached != null) {
            //System.out.println("💾 [CACHE HIT] Room " + roomId);
            return cached;
        }

        for (Building b : dao.findAll()) {
            for (Floor f : b.getFloors()) {
                for (Room r : f.getRooms()) {
                    if (r.getId().equals(roomId)) {
                        roomCache.put(roomId, r);
                        //System.out.println("📥 [CACHE PUT] Room " + roomId);
                        return r;
                    }
                }
            }
        }

        throw new NoSuchElementException("Room with id " + roomId + " not found");
    }


}
