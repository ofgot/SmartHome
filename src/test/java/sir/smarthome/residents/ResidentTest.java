package sir.smarthome.residents;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import sir.smarthome.residents.ResidentType;

class ResidentTest {
    @Test
    void testHumanCreation() {
        UUID id = UUID.randomUUID();
        String name = "Ofgot";
        Human human = new Human(id, name);

        assertNotNull(human.getId());
        assertEquals(id, human.getId());
        assertEquals(name, human.getName());
        assertEquals(ResidentType.HUMAN, human.getType());
    }

    @Test
    void testAnimalCreation() {
        UUID id = UUID.randomUUID();
        String name = "Whiskers";
        Animal animal = new Animal(id, name);

        assertNotNull(animal.getId());
        assertEquals(id, animal.getId());
        assertEquals(name, animal.getName());
        assertEquals(ResidentType.CAT, animal.getType());
    }

    @Test
    void testResidentType() {
        UUID humanId = UUID.randomUUID();
        Human human = new Human(humanId, "Alice");
        assertEquals(ResidentType.HUMAN, human.getType());

        UUID animalId = UUID.randomUUID();
        Animal animal = new Animal(animalId, "Felix");
        assertEquals(ResidentType.CAT, animal.getType());
    }
}