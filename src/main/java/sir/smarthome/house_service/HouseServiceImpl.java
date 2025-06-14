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
    public List<Building> listBuildings() {
        return dao.findAll();
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
            return cached;
        }

        for (Building b : dao.findAll()) {
            for (Floor f : b.getFloors()) {
                for (Room r : f.getRooms()) {
                    if (r.getId().equals(roomId)) {
                        roomCache.put(roomId, r);
                        return r;
                    }
                }
            }
        }

        throw new NoSuchElementException("Room with id " + roomId + " not found");
    }

}
