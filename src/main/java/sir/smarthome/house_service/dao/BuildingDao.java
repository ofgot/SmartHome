package sir.smarthome.house_service.dao;

import sir.smarthome.house_service.model.Building;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Data Access Object interface for building operations.
 * Defines CRUD operations for building entities.
 */
public interface BuildingDao {
    Optional<Building> findBuildingById(UUID buildingId);
    void saveBuilding(Building b);
    void deleteBuilding(UUID buildingId);
    List<Building> findAll();

}
