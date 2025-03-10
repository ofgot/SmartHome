package sir.smarthome.residents;

import java.util.UUID;

public abstract class Resident {
    private UUID id;
    private String name;
    private ResidentType type;

    public Resident(String name, ResidentType type) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.type = type;
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
