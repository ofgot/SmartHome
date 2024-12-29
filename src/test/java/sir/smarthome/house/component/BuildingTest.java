package sir.smarthome.house.component;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class BuildingTest {
    @Test
    void testAddAndRemoveFloor() {
        Building building = new Building("Office Building");
        Floor floor1 = new Floor("First Floor");

        building.addComponent(floor1);

        assertThat(building.getComponents()).contains(floor1);

        building.removeComponent(floor1);
        assertThat(building.getComponents()).doesNotContain(floor1);
    }
}