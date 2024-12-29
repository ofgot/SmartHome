package sir.smarthome.residents;

import java.util.UUID;

public abstract class Resident {
    private UUID id;
    private String name;
    private ResidentType type;
    public Resident(UUID id, String name, ResidentType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(ResidentType type) {
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
