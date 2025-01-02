package sir.smarthome;

import sir.smarthome.commands.*;
import sir.smarthome.common.Equipment;
import sir.smarthome.common.Product;
import sir.smarthome.devices.Device;
import sir.smarthome.devices.Fridge;
import sir.smarthome.devices.TV;
import sir.smarthome.factories.*;
import sir.smarthome.house.component.*;
import sir.smarthome.reports.*;
import sir.smarthome.residents.*;

public class SmartHomeApplication {

    public void run(){
        firstConfig();
    }

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
        // ScratchingPost
        Equipment scratchingPost = new Equipment("Scratching post");

        // Bicycles
        Equipment bike1 = new Equipment("Bike 1");
        Equipment bike2 = new Equipment("Bike 2");

        // Types ...
        DeviceFactory fridgeFactory = FridgeFactory.getInstance();
        DeviceFactory multimediaFactory = MultimediaFactory.getInstance();
        DeviceFactory sensorFactory = SensorFactory.getInstance();
        DeviceFactory computerFactory = ComputerFactory.getInstance();
        DeviceFactory stoveFactory = StoveFactory.getInstance();

        // TV
        Device tv1 = multimediaFactory.createDevice(1, "TV1");
        Device tv2 = multimediaFactory.createDevice(2,"TV2");
        Device tv3 = multimediaFactory.createDevice(2,"TV3");
        Device tv4 = multimediaFactory.createDevice(2,"TV4");

        // Fridge
        Device fridge1 = fridgeFactory.createDevice(2, "Fridge1");
        Device fridge2 = fridgeFactory.createDevice(3, "Fridge2");
        Device fridge3 = fridgeFactory.createDevice(1, "Fridge3");
        Device fridge4 = fridgeFactory.createDevice(2, "Fridge4");
        Device fridge5 = fridgeFactory.createDevice(2, "Fridge5");
        Device fridge6 = fridgeFactory.createDevice(1, "Fridge6");

        // Temperature Sensor
        Device temperatureKitchen = sensorFactory.createDevice(0.1, "Temperature sensor Kitchen");
        Device temperatureLivingRoom1 = sensorFactory.createDevice(0.1, "Temperature sensor livingRoom1");
        Device temperatureLivingRoom2 = sensorFactory.createDevice(0.1, "Temperature sensor livingRoom2");
        Device temperatureLivingRoom3 = sensorFactory.createDevice(0.1, "Temperature sensor livingRoom3");
        Device temperatureAdministration = sensorFactory.createDevice(0.1, "Temperature sensor administration");

        // Computer
        Device computer1 = computerFactory.createDevice(2, "Computer1");
        Device computer2 = computerFactory.createDevice(1, "Computer2");

        // Products
        Product milk = new Product("Milk");
        Product eggs = new Product("Eggs");
        Product butter = new Product("Butter");
        Product bread = new Product("Bread");

        // Stove
        Device stove1 = stoveFactory.createDevice(3, "Stove1");
        Device stove2 = stoveFactory.createDevice(3, "Stove2");
        Device stove3 = stoveFactory.createDevice(3, "Stove3");

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
        Room storeRoom = new Room("Room 9");

        // other
        restaurant.addComponent(floor1);

        floor1.addComponent(kitchen);
        floor1.addComponent(livingRoom1);
        floor1.addComponent(livingRoom2);
        floor1.addComponent(livingRoom3);
        floor1.addComponent(administration);
        floor1.addComponent(changeRoom);
        floor1.addComponent(toiletWoman);
        floor1.addComponent(toiletMan);
        floor1.addComponent(storeRoom);

        kitchen.addDevice(fridge1);
        kitchen.addDevice(fridge2);
        kitchen.addDevice(fridge3);
        kitchen.addDevice(fridge4);
        kitchen.addDevice(fridge5);
        kitchen.addDevice(fridge6);
        kitchen.addDevice(temperatureKitchen);
        kitchen.addDevice(stove1);
        kitchen.addDevice(stove2);
        kitchen.addDevice(stove3);
        kitchen.addResident(perdelko);
        kitchen.addResident(pepa);

        livingRoom1.addDevice(temperatureLivingRoom1);
        livingRoom1.addDevice(tv1);
        livingRoom1.addDevice(tv2);
        livingRoom1.addResident(cat1);
        livingRoom1.addResident(cat2);
        livingRoom1.addResident(cat3);
        livingRoom1.addResident(trpaslik);
        livingRoom1.addResident(permonik);

        livingRoom2.addDevice(temperatureLivingRoom2);
        livingRoom2.addDevice(tv3);
        livingRoom2.addResident(cat4);
        livingRoom2.addResident(cat5);

        livingRoom3.addDevice(temperatureLivingRoom3);
        livingRoom3.addDevice(tv4);
        livingRoom3.addResident(cat6);
        livingRoom3.addResident(cat7);

        administration.addDevice(computer1);
        administration.addDevice(computer2);
        administration.addDevice(temperatureAdministration);
        administration.addResident(dasa);
        administration.addResident(tim);

        storeRoom.addEquipment(scratchingPost);
        storeRoom.addEquipment(bike1);
        storeRoom.addEquipment(bike2);

        //reports
        HouseConfigurationReport houseConfigurationReport = new HouseConfigurationReport(restaurant);
        ConsumptionReportStrategy consumptionReportStrategy = new ConsumptionReportStrategy();
        ActivityReportStrategy activityReportStrategy = new ActivityReportStrategy();
        EventReportStrategy eventReportStrategy = new EventReportStrategy();

        // Commands
        Command increaseVolume = new IncreaseVolumeAction((TV) tv1, dasa, 5);
        Command decreaseVolume = new DecreaseVolumeAction((TV) tv3, tim, 3);

        Command loadProductMilk = new LoadProductAction((Fridge) fridge1, perdelko, milk);
        Command loadProductBread = new LoadProductAction((Fridge) fridge2, perdelko, bread);
        Command loadProductEggs = new LoadProductAction((Fridge) fridge2, perdelko, eggs);

        Command takeProductMilk = new TakeProductAction((Fridge) fridge1, pepa, milk);
        Command takeProductEggs = new TakeProductAction((Fridge) fridge2, pepa, milk);

        Command turnOffTV = new TurnOffDeviceAction(tv4, tim);

        // Execute
        ReportGenerator reportGenerator = new ReportGenerator(activityReportStrategy);
        DeviceApi api = new DeviceApi(reportGenerator);

        api.setAction(increaseVolume);
        api.executeAction();

        api.setAction(decreaseVolume);
        api.executeAction();

        api.setAction(loadProductMilk);
        api.executeAction();

        api.setAction(loadProductBread);
        api.executeAction();

        api.setAction(loadProductEggs);
        api.executeAction();

        api.setAction(takeProductMilk);
        api.executeAction();

        api.setAction(takeProductEggs);
        api.executeAction();

        api.setAction(turnOffTV);
        api.executeAction();

        // reports
        System.out.println(houseConfigurationReport.generateReport());
        System.out.println(reportGenerator.generateReport());
    }

    public Building secondConfig(){
        // Residents
        // HUMAN
        Human george = new Human("George"); // Father
        Human lily = new Human("Lily");   // Mother
        Human oscar = new Human("Oscar");  // Son
        Human mia = new Human("Mia");    // Daughter
        Human max = new Human("Max");    // Son
        Human sophia = new Human("Sophia"); // Daughter

        // ANIMALS
        Animal hamster = new Animal("Squeaky McSqueakface");
        Animal parrot = new Animal("Sir Tweets-a-Lot");

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
        DeviceFactory stoveFactory = StoveFactory.getInstance();

        // TV
        Device tv1 = multimediaFactory.createDevice(1, "TV1");
        Device tv2 = multimediaFactory.createDevice(2,"TV2");
        Device tv3 = multimediaFactory.createDevice(1,"TV3");
        Device tv4 = multimediaFactory.createDevice(2,"TV4");
        Device tv5 = multimediaFactory.createDevice(1,"TV5");
        Device tv6 = multimediaFactory.createDevice(2,"TV6");

        // Fridge
        Device fridge1 = fridgeFactory.createDevice(2, "Fridge1");
        Device fridge2 = fridgeFactory.createDevice(3, "Fridge2");
        Device stove1 = stoveFactory.createDevice(4, "Stove1");
        Device stove2 = stoveFactory.createDevice(4, "Stove2");

        // Temperature Sensor
        Device temperatureKitchen = sensorFactory.createDevice(0.1, "Temperature sensor Kitchen");
        Device temperatureLivingRoom = sensorFactory.createDevice(0.1, "Temperature sensor livingRoom");
        Device temperatureDinningRoom = sensorFactory.createDevice(0.1, "Dinning room");
        Device temperatureParents = sensorFactory.createDevice(0.1, "Temperature sensor parents room");
        Device temperatureOscar = sensorFactory.createDevice(0.1, "Temperature sensor Oscar room");
        Device temperatureMia = sensorFactory.createDevice(0.1, "Temperature sensor Mia room");
        Device temperatureMax = sensorFactory.createDevice(0.1, "Temperature sensor Max room");
        Device temperatureSophia = sensorFactory.createDevice(0.1, "Temperature sensor Sophia room");
        Device temperatureOffice = sensorFactory.createDevice(0.1, "Temperature sensor office");

        // Computer
        Device computerFather = computerFactory.createDevice(5, "Computer Father");
        Device computerMother = computerFactory.createDevice(7, "Computer Mother");
        Device computerOscar = computerFactory.createDevice(4, "Computer Oscar");
        Device computerMia = computerFactory.createDevice(4, "Computer Mia");
        Device computerMax = computerFactory.createDevice(4, "Computer Max");
        Device computerSophia = computerFactory.createDevice(4, "Computer Sophia");

        // Building
        Building stalinHouse = new Building("Stalin's House");

        // Floor
        Floor floor1 = new Floor("Floor 1");
        Floor floor2 = new Floor("Floor 2");

        // Rooms
        Room kitchen = new Room("Kitchen");
        Room livingRoom1 = new Room("Living Room");
        Room dinningRoom = new Room("Dinning Room");
        Room parentsBedRoom = new Room("Parents Bed Room");
        Room oscarBedRoom = new Room("Oscar Bed Room");
        Room miaBedRoom = new Room("Mia Bed Room");
        Room maxBedRoom = new Room("Max Bed Room");
        Room sophiaBedRoom = new Room("Sophia Bed Room");
        Room toilet1 = new Room("Toilet 1");
        Room toilet2 = new Room("Toilet 2");
        Room toilet3 = new Room("Toilet 3");
        Room office = new Room("Office");
        Room store = new Room("Store");

        // other
        stalinHouse.addComponent(floor1);
        stalinHouse.addComponent(floor2);

        floor1.addComponent(kitchen);
        floor1.addComponent(livingRoom1);
        floor1.addComponent(toilet1);
        floor1.addComponent(office);
        floor2.addComponent(store);

        floor2.addComponent(parentsBedRoom);
        floor2.addComponent(oscarBedRoom);
        floor2.addComponent(miaBedRoom);
        floor2.addComponent(maxBedRoom);
        floor2.addComponent(sophiaBedRoom);
        floor2.addComponent(toilet2);
        floor2.addComponent(toilet3);

        store.addEquipment(bike1);
        store.addEquipment(bike2);
        store.addEquipment(ski1);

        kitchen.addDevice(stove1);
        kitchen.addDevice(stove2);
        kitchen.addDevice(temperatureKitchen);
        kitchen.addDevice(fridge1);
        kitchen.addDevice(fridge2);
        kitchen.addDevice(tv1);

        livingRoom1.addDevice(temperatureLivingRoom);
        livingRoom1.addDevice(tv2);

        dinningRoom.addDevice(temperatureDinningRoom);

        office.addDevice(temperatureOffice);
        office.addDevice(computerFather);
        office.addDevice(computerMother);
        office.addDevice(tv3);

        parentsBedRoom.addDevice(temperatureParents);
        parentsBedRoom.addDevice(tv4);
        parentsBedRoom.addResident(george);
        parentsBedRoom.addResident(lily);

        oscarBedRoom.addDevice(temperatureOscar);
        oscarBedRoom.addDevice(computerOscar);
        oscarBedRoom.addDevice(tv5);
        oscarBedRoom.addResident(oscar);

        miaBedRoom.addDevice(temperatureMia);
        miaBedRoom.addDevice(computerMia);
        miaBedRoom.addDevice(tv6);
        miaBedRoom.addResident(mia);

        maxBedRoom.addDevice(temperatureMax);
        maxBedRoom.addDevice(computerMax);
        maxBedRoom.addResident(max);
        maxBedRoom.addResident(sophia);
        maxBedRoom.addResident(hamster);
        maxBedRoom.addResident(parrot);

        sophiaBedRoom.addDevice(temperatureSophia);
        maxBedRoom.addDevice(computerSophia);

        return stalinHouse;
    }
}
