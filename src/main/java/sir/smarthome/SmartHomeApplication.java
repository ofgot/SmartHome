package sir.smarthome;

import sir.smarthome.devices.Device;
import sir.smarthome.factories.*;
import sir.smarthome.house.component.Building;
import sir.smarthome.house.component.Floor;
import sir.smarthome.house.component.Room;
import sir.smarthome.reports.HouseConfigurationReport;

public class SmartHomeApplication {
    public static void main(String[] args) {
        DeviceFactory computerFactory = ComputerFactory.getInstance();
        DeviceFactory fridgeFactory = FridgeFactory.getInstance();
        DeviceFactory multimediaFactory = MultimediaFactory.getInstance();

        Device computer = computerFactory.createDevice();
        Device fridge = fridgeFactory.createDevice();
        Device tv = multimediaFactory.createDevice();

        // building
        Building building = new Building("Smart Home");

        // floor
        Floor firstFloor = new Floor("First Floor");
        Floor secondFloor = new Floor("Second Floor");
        Floor thirdFloor = new Floor("Third Floor");

        // room
        Room kitchen = new Room("Kitchen");
        Room livingRoom = new Room("Living Room");
        Room office = new Room("Office");

        kitchen.addDevice(fridge);
        livingRoom.addDevice(tv);
        office.addDevice(computer);

        firstFloor.addComponent(kitchen);
        secondFloor.addComponent(livingRoom);
        thirdFloor.addComponent(office);

        building.addComponent(firstFloor);
        building.addComponent(secondFloor);
        building.addComponent(thirdFloor);

        HouseConfigurationReport report = new HouseConfigurationReport(building);
        System.out.println(report.generateReport());
    }
}
