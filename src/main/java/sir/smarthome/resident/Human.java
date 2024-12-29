package sir.smarthome.resident;

import java.util.UUID;

public class Human extends Resident {
    public Human(UUID id, String name) {
        super(id, name, ResidentType.HUMAN);
    }
}