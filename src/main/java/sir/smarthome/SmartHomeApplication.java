package sir.smarthome;

import sir.smarthome.common.Equipment;
import sir.smarthome.devices.Device;
import sir.smarthome.factories.*;
import sir.smarthome.house.component.Building;
import sir.smarthome.house.component.Floor;
import sir.smarthome.house.component.Room;
import sir.smarthome.residents.Animal;
import sir.smarthome.residents.Human;

public class SmartHomeApplication {
    public void firstConfig(){
        // Residents
        // HUMAN
        Human perdelko = new Human("Perdelko"); // cooker
        Human pepa = new Human("Pepa");         // cooker
        Human trpaslik = new Human("Trpaslik"); // Customer
        Human permonik = new Human("Permonik"); // Customer
        Human dasa = new Human("Dasa");         // Owner
        Human tim = new Human("Tim");           // Owner

        // ANIMALS
        Animal cat1 = new Animal("Whiskers");
        Animal cat2 = new Animal("Rex");
        Animal cat3 = new Animal("Fluffy");
        Animal cat4 = new Animal("Kittler");
        Animal cat5 = new Animal("klobouk");
        Animal cat6 = new Animal("Broucek");
        Animal cat7 = new Animal("Fattik");

        // Equipments
        // Skis
        Equipment ski1 = new Equipment("Ski 1");
        // Bicycles
        Equipment bike1 = new Equipment("Bike 1");
        Equipment bike2 = new Equipment("Bike 2");

        // Types ...
        DeviceFactory fridgeFactory = FridgeFactory.getInstance();
        DeviceFactory multimediaFactory = MultimediaFactory.getInstance();
        DeviceFactory sensorFactory = SensorFactory.getInstance();
        DeviceFactory computerFactory = ComputerFactory.getInstance();

        // TV
        Device tv1 = multimediaFactory.createDevice(1);
        Device tv2 = multimediaFactory.createDevice(2);

        // Fridge
        Device fridge1 = fridgeFactory.createDevice(2);
        Device fridge2 = fridgeFactory.createDevice(3);
        Device fridge3 = fridgeFactory.createDevice(1);
        Device fridge4 = fridgeFactory.createDevice(1);

        // Temperature Sensor
        Device temperatureKitchen = sensorFactory.createDevice(0.1);
        Device temperatureLivingRoom1 = sensorFactory.createDevice(0.1);
        Device temperatureLivingRoom2 = sensorFactory.createDevice(0.1);
        Device temperatureLivingRoom3 = sensorFactory.createDevice(0.1);
        Device temperatureAdministration = sensorFactory.createDevice(0.1);

        // Computer
        Device computer1 = computerFactory.createDevice(2);
        Device computer2 = computerFactory.createDevice(1);

        // Stove


        // Building
        Building restaurant = new Building("Cat Cafe");

        // Floor
        Floor floor1 = new Floor("Floor 1");

        // Rooms
        Room kitchen = new Room("Room 1");
        Room livingRoom1 = new Room("Room 2");
        Room livingRoom2 = new Room("Room 3");
        Room livingRoom3 = new Room("Room 4");
        Room toiletWoman = new Room("Room 5");
        Room toiletMan = new Room("Room 6");
        Room administration = new Room("Room 7");
        Room changeRoom = new Room("Room 8");
    }

    public void secondConfig(){
        // Residents
        // HUMAN
        Human human1 = new Human("George");
        Human human2 = new Human("Lily");
        Human human3 = new Human("Oscar");
        Human human4 = new Human("Mia");
        Human human5 = new Human("Max");
        Human human6 = new Human("Sophia");

        // ANIMALS
        Animal hamster = new Animal("Squeaky McSqueakface");
        Animal parrot = new Animal("Sir Tweets-a-Lot");
        Animal turtle = new Animal("Shelldon");
        Animal guineaPig = new Animal("Pignut");
        Animal fish = new Animal("Finn McCool");

        // Skis
        Equipment ski1 = new Equipment("Ski 1");
        // Bicycles
        Equipment bike1 = new Equipment("Bike 1");
        Equipment bike2 = new Equipment("Bike 2");

        // Types ...
        // TV

        // Fridge

        // Temperature Sensor

        // Computer

        // Building

        // Floor

        // Rooms


    }
}
