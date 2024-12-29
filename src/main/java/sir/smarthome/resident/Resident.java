package sir.smarthome.resident;

import java.util.UUID;

public abstract class Resident {
    private final UUID id;
    private String name;
    private ResidentType type;

    public Resident(UUID id, String name, ResidentType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ResidentType getType() {
        return type;
    }
}
