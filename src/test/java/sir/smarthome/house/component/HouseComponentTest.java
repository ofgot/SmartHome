package sir.smarthome.house.component;
//import sir.smarthome.house_service.model.HouseComponent;

//public class HouseComponentTest {
//
//    private Building building;
//    private Floor floor1;
//    private Floor floor2;
//    private Room room1;
//    private Room room2;
//
//    @BeforeEach
//    public void setUp() {
//        building = new Building("Main Building");
//        floor1 = new Floor("First Floor");
//        floor2 = new Floor("Second Floor");
//        room1 = new Room("Room 101");
//        room2 = new Room("Room 102");
//
//        floor1.addComponent(room1);
//        floor2.addComponent(room2);
//        building.addComponent(floor1);
//        building.addComponent(floor2);
//    }
//
//    @Test
//    public void testBuildingStructure() {
//        List<HouseComponent> floors = building.getComponents();
//        assertEquals(2, floors.size(), "Building should have 2 floors");
//        assertEquals("First Floor", floors.get(0).getName());
//        assertEquals("Second Floor", floors.get(1).getName());
//    }
//
//    @Test
//    public void testFloorStructure() {
//        List<HouseComponent> rooms = floor1.getComponents();
//        assertEquals(1, rooms.size(), "First Floor should have 1 room");
//        assertEquals("Room 101", rooms.get(0).getName());
//    }
//
//    @Test
//    public void testRoomLeafBehavior() {
//        assertThrows(UnsupportedOperationException.class, () -> room1.addComponent(new Room("Room 103")));
//    }
//
//    @Test
//    public void testAppendReport() {
//        StringBuilder report = new StringBuilder();
//        building.appendReport(report);
//
//        String expectedReport = """
//                Building: Main Building
//                ==========================
//                Floor: First Floor
//                Room: Room 101
//                  No devices
//                  No equipment
//                ==========================
//                Floor: Second Floor
//                Room: Room 102
//                  No devices
//                  No equipment
//                """.strip();
//
//        assertEquals(expectedReport, report.toString().strip(), "Report should match expected structure");
//    }
//}
