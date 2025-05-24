package sir.smarthome.house_service.dao;

import sir.smarthome.house_service.model.Building;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// to get building to later get floors and rooms from it
public interface BuildingDao {
    Optional<Building> findBuildingById(UUID buildingId);
    void saveBuilding(Building b);
    void deleteBuilding(UUID buildingId);
    List<Building> findAll();

}
