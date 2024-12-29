package sir.smarthome.house.component;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class FloorTest {

    @Test
    void testAddAndRemoveRoom() {
        Floor floor = new Floor("Ground Floor");
        Room room101 = new Room("Room 101");

        floor.addComponent(room101);

        assertThat(floor.getComponents()).contains(room101);

        floor.removeComponent(room101);
        assertThat(floor.getComponents()).doesNotContain(room101);
    }
}