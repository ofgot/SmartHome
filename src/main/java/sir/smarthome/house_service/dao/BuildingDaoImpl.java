package sir.smarthome.house_service.dao;

import sir.smarthome.house_service.model.Building;

import java.util.*;

import java.util.*;

/**
 * In-memory implementation of the {@link BuildingDao} interface.
 * Stores buildings in a local {@link HashMap} for quick access and testing purposes.
 *
 * <p>This class is typically used for development or testing where persistence
 * to a real database is not required.</p>
 */
public class BuildingDaoImpl implements BuildingDao {

    private final Map<UUID, Building> buildings = new HashMap<>();

    /**
     * Finds a building by its unique identifier.
     *
     * @param buildingId the UUID of the building to find
     * @return an {@link Optional} containing the building if found, or empty if not
     */
    @Override
    public Optional<Building> findBuildingById(UUID buildingId) {
        return Optional.ofNullable(buildings.get(buildingId));
    }

    /**
     * Saves a building to the internal store. If a building with the same ID exists,
     * it will be overwritten.
     *
     * @param b the {@link Building} to save
     */
    @Override
    public void saveBuilding(Building b) {
        buildings.put(b.getId(), b);
    }

    /**
     * Deletes a building by its UUID.
     *
     * @param buildingId the UUID of the building to delete
     */
    @Override
    public void deleteBuilding(UUID buildingId) {
        buildings.remove(buildingId);
    }

    /**
     * Returns a list of all stored buildings.
     *
     * @return a {@link List} of all {@link Building} objects
     */
    @Override
    public List<Building> findAll() {
        return new ArrayList<>(buildings.values());
    }
}

