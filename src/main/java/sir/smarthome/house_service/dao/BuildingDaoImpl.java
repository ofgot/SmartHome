package sir.smarthome.house_service.dao;

import sir.smarthome.house_service.model.Building;

import java.util.*;

public class BuildingDaoImpl implements BuildingDao {

    private final Map<UUID, Building> buildings = new HashMap<>();

    @Override
    public Optional<Building> findBuildingById(UUID buildingId) {
        return Optional.ofNullable(buildings.get(buildingId));
    }

    @Override
    public void saveBuilding(Building b) {
        buildings.put(b.getId(), b);
    }

    @Override
    public void deleteBuilding(UUID buildingId) {
        buildings.remove(buildingId);
    }

    @Override
    public List<Building> findAll() {
        return new ArrayList<>(buildings.values());
    }
}
