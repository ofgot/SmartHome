package sir.smarthome.residents;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ResidentTest {
    @Test
    void testHumanCreation() {
        UUID id = UUID.randomUUID();
        String name = "Ofgot";
        Human human = new Human(name);

        assertNotNull(human.getId());
        assertEquals(name, human.getName());
        assertEquals(ResidentType.HUMAN, human.getType());
    }

    @Test
    void testAnimalCreation() {
        UUID id = UUID.randomUUID();
        String name = "Whiskers";
        Animal animal = new Animal(name);

        assertNotNull(animal.getId());
        assertEquals(name, animal.getName());
        assertEquals(ResidentType.CAT, animal.getType());
    }

    @Test
    void testResidentType() {
        UUID humanId = UUID.randomUUID();
        Human human = new Human("Alice");
        assertEquals(ResidentType.HUMAN, human.getType());

        UUID animalId = UUID.randomUUID();
        Animal animal = new Animal("Felix");
        assertEquals(ResidentType.CAT, animal.getType());
    }
}