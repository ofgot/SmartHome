package sir.smarthome.residents;

import java.util.UUID;

public class Animal extends Resident {
    public Animal(UUID id, String name) {
        super(id, name, ResidentType.CAT);
    }
}
