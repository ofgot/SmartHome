package sir.smarthome;

import sir.smarthome.commands.*;
import sir.smarthome.common.Equipment;
import sir.smarthome.common.Product;
import sir.smarthome.devices.*;
import sir.smarthome.factories.*;
import sir.smarthome.house.component.*;
import sir.smarthome.observers.HeatingSystemObserver;
import sir.smarthome.reports.*;
import sir.smarthome.residents.*;

public class SmartHomeApplication {
    /**
     * Executes a specific configuration based on the provided input.
     *
     * @param in the input integer that determines the configuration to execute.
     *           - If the input is 1, the {@code firstConfig()} method is invoked.
     *           - If the input is 2, the {@code secondConfig()} method is invoked.
     *           - For any other input, an "Invalid input" message is printed to the console.
     * @throws InterruptedException if the execution of a configuration is interrupted.
     */
    public void run(Integer in) throws InterruptedException {
        switch (in) {
            case 1:
                firstConfig();
                break;
            case 2:
                secondConfig();
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    /**
     * Configures the simulation environment by creating a building, residents, devices, and activities.
     *
     * <p>The configuration includes the following:</p>
     *
     * <h3>Building and Layout:</h3>
     * <ul>
     *   <li>A single building named "Cat Cafe" is created.</li>
     *   <li>The building contains one floor with multiple rooms, including:
     *       <ul>
     *         <li>Kitchen</li>
     *         <li>Living rooms</li>
     *         <li>Administration office</li>
     *         <li>Store room</li>
     *         <li>Changing room</li>
     *         <li>Restrooms (for men and women)</li>
     *       </ul>
     *   </li>
     * </ul>
     *
     * <h3>Residents:</h3>
     * <ul>
     *   <li><b>Humans:</b> Six human residents are created with specific roles:
     *       <ul>
     *         <li>Cooks (e.g., Perdelko, Pepa)</li>
     *         <li>Customers (e.g., Trpaslik, Permonik)</li>
     *         <li>Owners (e.g., Dasa, Tim)</li>
     *       </ul>
     *   </li>
     *   <li><b>Animals:</b> Seven animal residents (cats) are added to simulate a cafe environment.</li>
     * </ul>
     *
     * <h3>Devices and Equipment:</h3>
     * <ul>
     *   <li>Devices like TVs, fridges, stoves, temperature sensors, and computers are created using factories.</li>
     *   <li>Equipment such as a scratching post and bicycles are added to the store room.</li>
     * </ul>
     *
     * <h3>Room Assignments:</h3>
     * <ul>
     *   <li>Devices and residents are assigned to specific rooms based on their roles and functions:
     *       <ul>
     *         <li>The kitchen contains fridges, stoves, a temperature sensor, and cooks.</li>
     *         <li>Living rooms contain TVs, temperature sensors, animals, and customers.</li>
     *         <li>The administration office contains computers, a temperature sensor, and owners.</li>
     *       </ul>
     *   </li>
     * </ul>
     *
     * <h3>Observers and Actions:</h3>
     * <ul>
     *   <li>Temperature sensors are configured with observers to monitor and react to changes.</li>
     *   <li>Commands for actions such as volume adjustments, product loading/unloading, and temperature checks are created and executed.</li>
     * </ul>
     *
     * <h3>Reporting:</h3>
     * <ul>
     *   <li>Reports are generated using different strategies, including activity, consumption, and event reporting.</li>
     *   <li>A house configuration report provides a summary of the building setup.</li>
     * </ul>
     *
     * <h3>Simulation Execution:</h3>
     * <ul>
     *   <li>The configured environment is used to simulate real-world interactions, execute commands, and generate reports.</li>
     * </ul>
     *
     * @throws InterruptedException if the thread is interrupted during the execution of the configuration.
     */
    public void firstConfig() throws InterruptedException {
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
        Device tv1 = multimediaFactory.createDevice(100, "TV1");
        Device tv2 = multimediaFactory.createDevice(200, "TV2");
        Device tv3 = multimediaFactory.createDevice(200, "TV3");
        Device tv4 = multimediaFactory.createDevice(200, "TV4");

        // Fridge
        Device fridge1 = fridgeFactory.createDevice(200, "Fridge1");
        Device fridge2 = fridgeFactory.createDevice(300, "Fridge2");
        Device fridge3 = fridgeFactory.createDevice(100, "Fridge3");
        Device fridge4 = fridgeFactory.createDevice(200, "Fridge4");
        Device fridge5 = fridgeFactory.createDevice(200, "Fridge5");
        Device fridge6 = fridgeFactory.createDevice(100, "Fridge6");

        // Temperature Sensor
        HeatingSystemObserver heatingSystemObserver = new HeatingSystemObserver();
        Device temperatureKitchen = sensorFactory.createDevice(10, "Temperature sensor Kitchen");
        Device temperatureLivingRoom1 = sensorFactory.createDevice(10, "Temperature sensor livingRoom1");
        Device temperatureLivingRoom2 = sensorFactory.createDevice(10, "Temperature sensor livingRoom2");
        Device temperatureLivingRoom3 = sensorFactory.createDevice(10, "Temperature sensor livingRoom3");
        Device temperatureAdministration = sensorFactory.createDevice(10, "Temperature sensor administration");

        TemperatureSensor kitchenSensor = (TemperatureSensor) temperatureKitchen;
        TemperatureSensor livingRoomSensor1 = (TemperatureSensor) temperatureLivingRoom1;
        TemperatureSensor livingRoomSensor2 = (TemperatureSensor) temperatureLivingRoom2;
        TemperatureSensor livingRoomSensor3 = (TemperatureSensor) temperatureLivingRoom3;
        TemperatureSensor administrationSensor = (TemperatureSensor) temperatureAdministration;

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

        // Observer
        kitchenSensor.addObserver(heatingSystemObserver);
        livingRoomSensor1.addObserver(heatingSystemObserver);
        livingRoomSensor2.addObserver(heatingSystemObserver);
        livingRoomSensor3.addObserver(heatingSystemObserver);
        administrationSensor.addObserver(heatingSystemObserver);

        // Reports
        HouseConfigurationReport houseConfigurationReport = new HouseConfigurationReport(restaurant);
        ConsumptionReportStrategy consumptionReportStrategy = new ConsumptionReportStrategy();
        ActivityReportStrategy activityReportStrategy = new ActivityReportStrategy();
        EventReportStrategy eventReportStrategy = new EventReportStrategy();

        ReportGenerator reportGenerator = new ReportGenerator(activityReportStrategy);

        // Commands
        Command increaseVolume = new IncreaseVolumeAction((TV) tv1, dasa, 5);
        Command decreaseVolume = new DecreaseVolumeAction((TV) tv3, tim, 3);

        Command loadProductMilk = new LoadProductAction((Fridge) fridge1, perdelko, milk);
        Command loadProductBread = new LoadProductAction((Fridge) fridge2, perdelko, bread);
        Command loadProductEggs = new LoadProductAction((Fridge) fridge2, perdelko, eggs);
        Command loadProductButter = new LoadProductAction((Fridge) fridge2, perdelko, butter);

        Command takeProductMilk = new TakeProductAction((Fridge) fridge1, pepa, milk);
        Command takeProductEggs = new TakeProductAction((Fridge) fridge2, pepa, milk);

        Command turnOffTV = new TurnOffDeviceAction(tv4, tim);

        Command checkKitchenTemperature = new CheckTemperature(kitchenSensor, heatingSystemObserver);
        Command checkLivingRoom1Temperature = new CheckTemperature(livingRoomSensor1, heatingSystemObserver);
        Command checkLivingRoom2Temperature = new CheckTemperature(livingRoomSensor2, heatingSystemObserver);
        Command checkLivingRoom3Temperature = new CheckTemperature(livingRoomSensor3, heatingSystemObserver);
        Command administrationRoom3Temperature = new CheckTemperature(administrationSensor, heatingSystemObserver);

        // Execute activityReportStrategy
        DeviceApi api = new DeviceApi(reportGenerator);

        api.setAction(increaseVolume);
        api.executeAction();

        api.setAction(decreaseVolume);
        api.executeAction();

        api.setAction(loadProductMilk);
        api.executeAction();

        api.setAction(loadProductBread);
        api.executeAction();

        api.setAction(loadProductButter);
        api.executeAction();

        api.setAction(loadProductEggs);
        api.executeAction();

        api.setAction(takeProductMilk);
        api.executeAction();

        api.setAction(takeProductEggs);
        api.executeAction();

        api.setAction(turnOffTV);
        api.executeAction();

        api.setAction(checkKitchenTemperature);
        api.executeAction();

        api.setAction(checkLivingRoom1Temperature);
        api.executeAction();

        api.setAction(checkLivingRoom2Temperature);
        api.executeAction();

        api.setAction(checkLivingRoom3Temperature);
        api.executeAction();

        api.setAction(administrationRoom3Temperature);
        api.executeAction();

        System.out.println(reportGenerator.generateReport());
        reportGenerator.setReportStrategy(eventReportStrategy);
        System.out.println(reportGenerator.generateReport());

        // Execute consumptionReportStrategy
        reportGenerator.setReportStrategy(consumptionReportStrategy);

        // warnings ///////////////////////////////////////////////////

        Thread.sleep(1000); // 1 sec

        ///////////////////////////////////////////////////////////////

        System.out.println(reportGenerator.generateReport());

        // report houseConfigurationReport
        System.out.println(houseConfigurationReport.generateReport());
    }

    /// /// second config ==========================================================================================================
    /**
     * Configures a complex simulation environment featuring a residential house, devices, sensors,
     * residents (both humans and animals), and various commands to simulate real-world activities.
     *
     * <h3>Residents:</h3>
     * <ul>
     *   <li><b>Humans:</b> A family of six individuals:
     *       <ul>
     *         <li>Father: Josef</li>
     *         <li>Mother: Nadya</li>
     *         <li>Children: Jakov, Svetlana, Vasiliy, Artem</li>
     *       </ul>
     *   </li>
     *   <li><b>Animals:</b>
     *       <ul>
     *         <li>Hamster: Squeaky McSqueakface</li>
     *         <li>Parrot: Sir Tweets-a-Lot</li>
     *       </ul>
     *   </li>
     * </ul>
     *
     * <h3>Building Layout:</h3>
     * <ul>
     *   <li><b>Building:</b> Stalin's House</li>
     *   <li><b>Floors:</b> Two floors</li>
     *   <li><b>Rooms:</b>
     *       <ul>
     *         <li>Floor 1: Kitchen, Living Room, Office, Toilet 1</li>
     *         <li>Floor 2: Bedrooms (Parents, Oscar, Mia, Max, Sophia), Store, Toilets 2 and 3</li>
     *       </ul>
     *   </li>
     * </ul>
     *
     * <h3>Devices and Equipment:</h3>
     * <ul>
     *   <li>Devices include TVs, fridges, stoves, computers, and temperature sensors, created via factories.</li>
     *   <li>Equipment includes bicycles and skis, stored in the store room.</li>
     * </ul>
     *
     * <h3>Assignments:</h3>
     * <ul>
     *   <li>Rooms are populated with residents, devices, and sensors based on their purpose:
     *       <ul>
     *         <li>Kitchen: Stoves, fridges, a temperature sensor, and a TV.</li>
     *         <li>Bedrooms: Computers, TVs, and temperature sensors, with residents assigned to specific rooms.</li>
     *         <li>Office: Computers, a TV, and a temperature sensor.</li>
     *       </ul>
     *   </li>
     * </ul>
     *
     * <h3>Observers and Sensors:</h3>
     * <ul>
     *   <li>Temperature sensors are assigned to key rooms and observed by a HeatingSystemObserver.</li>
     * </ul>
     *
     * <h3>Reports:</h3>
     * <ul>
     *   <li>Various reports generated, including house configuration, consumption, activity, and event reports.</li>
     * </ul>
     *
     * <h3>Commands and Actions:</h3>
     * <ul>
     *   <li>Commands simulate actions such as turning devices on/off, loading products into fridges, and checking room temperatures.</li>
     *   <li>Commands are executed via a DeviceApi, which uses a configurable ReportGenerator to track activities.</li>
     * </ul>
     *
     * <h3>Simulation Execution:</h3>
     * <ul>
     *   <li>Commands are sequentially executed, affecting the simulated environment and generating reports.</li>
     *   <li>Delays (e.g., Thread.sleep) simulate real-world timing.</li>
     * </ul>
     *
     * <h3>Outputs:</h3>
     * <ul>
     *   <li>Reports provide insights into house configuration, energy consumption, and activity tracking.</li>
     * </ul>
     *
     * @throws InterruptedException if the thread is interrupted during the execution of the simulation.
     */
    public void secondConfig() throws InterruptedException {
        // Residents
        // HUMAN
        Human josef = new Human("Josef");       // Father
        Human nadya = new Human("Nadezda");     // Mother
        Human jakov = new Human("Jakov");       // Son
        Human sveta = new Human("Svetlana");    // Daughter
        Human vasya = new Human("Vasiliy");     // Son
        Human artem = new Human("Artem");     // Son

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
        Device tv1 = multimediaFactory.createDevice(10, "TV1");
        Device tv2 = multimediaFactory.createDevice(20, "TV2");
        Device tv3 = multimediaFactory.createDevice(15, "TV3");
        Device tv4 = multimediaFactory.createDevice(20, "TV4");
        Device tv5 = multimediaFactory.createDevice(10, "TV5");
        Device tv6 = multimediaFactory.createDevice(20, "TV6");

        // Fridge
        Device fridge1 = fridgeFactory.createDevice(20, "Fridge1");
        Device fridge2 = fridgeFactory.createDevice(37, "Fridge2");
        Device stove1 = stoveFactory.createDevice(43, "Stove1");
        Device stove2 = stoveFactory.createDevice(42, "Stove2");

        // Temperature Sensor
        HeatingSystemObserver heatingSystemObserver = new HeatingSystemObserver();
        Device temperatureKitchen = sensorFactory.createDevice(10, "Temperature sensor Kitchen");
        Device temperatureLivingRoom = sensorFactory.createDevice(10, "Temperature sensor livingRoom");
        Device temperatureDinningRoom = sensorFactory.createDevice(10, "Dinning room");
        Device temperatureParents = sensorFactory.createDevice(10, "Temperature sensor parents room");
        Device temperatureOscar = sensorFactory.createDevice(10, "Temperature sensor Oscar room");
        Device temperatureMia = sensorFactory.createDevice(10, "Temperature sensor Mia room");
        Device temperatureMax = sensorFactory.createDevice(10, "Temperature sensor Max room");
        Device temperatureSophia = sensorFactory.createDevice(10, "Temperature sensor Sophia room");
        Device temperatureOffice = sensorFactory.createDevice(10, "Temperature sensor office");

        TemperatureSensor kitchenSensor = (TemperatureSensor) temperatureKitchen;
        TemperatureSensor livingRoomSensor = (TemperatureSensor) temperatureLivingRoom;
        TemperatureSensor dinningRoomSensor = (TemperatureSensor) temperatureDinningRoom;
        TemperatureSensor parentsRoomSensor = (TemperatureSensor) temperatureParents;
        TemperatureSensor oscarRoomSensor = (TemperatureSensor) temperatureOscar;
        TemperatureSensor miaRoomSensor = (TemperatureSensor) temperatureMia;
        TemperatureSensor maxRoomSensor = (TemperatureSensor) temperatureMax;
        TemperatureSensor sophiaRoomSensor = (TemperatureSensor) temperatureSophia;
        TemperatureSensor officeSensor = (TemperatureSensor) temperatureOffice;

        // Computer
        Device computerFather = computerFactory.createDevice(53, "Computer Father");
        Device computerMother = computerFactory.createDevice(62, "Computer Mother");
        Device computerOscar = computerFactory.createDevice(20, "Computer Oscar");
        Device computerMia = computerFactory.createDevice(20, "Computer Mia");
        Device computerMax = computerFactory.createDevice(23, "Computer Max");
        Device computerSophia = computerFactory.createDevice(23, "Computer Sophia");

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
        parentsBedRoom.addResident(josef);
        parentsBedRoom.addResident(nadya);

        oscarBedRoom.addDevice(temperatureOscar);
        oscarBedRoom.addDevice(computerOscar);
        oscarBedRoom.addDevice(tv5);
        oscarBedRoom.addResident(jakov);

        miaBedRoom.addDevice(temperatureMia);
        miaBedRoom.addDevice(computerMia);
        miaBedRoom.addDevice(tv6);
        miaBedRoom.addResident(sveta);

        maxBedRoom.addDevice(temperatureMax);
        maxBedRoom.addDevice(computerMax);
        maxBedRoom.addResident(vasya);
        maxBedRoom.addResident(artem);
        maxBedRoom.addResident(hamster);
        maxBedRoom.addResident(parrot);

        sophiaBedRoom.addDevice(temperatureSophia);
        maxBedRoom.addDevice(computerSophia);

        // Observer
        kitchenSensor.addObserver(heatingSystemObserver);
        livingRoomSensor.addObserver(heatingSystemObserver);
        dinningRoomSensor.addObserver(heatingSystemObserver);
        parentsRoomSensor.addObserver(heatingSystemObserver);
        oscarRoomSensor.addObserver(heatingSystemObserver);
        miaRoomSensor.addObserver(heatingSystemObserver);
        maxRoomSensor.addObserver(heatingSystemObserver);
        sophiaRoomSensor.addObserver(heatingSystemObserver);
        officeSensor.addObserver(heatingSystemObserver);

        // Reports
        HouseConfigurationReport houseConfigurationReport = new HouseConfigurationReport(stalinHouse);
        ConsumptionReportStrategy consumptionReportStrategy = new ConsumptionReportStrategy();
        ActivityReportStrategy activityReportStrategy = new ActivityReportStrategy();
        EventReportStrategy eventReportStrategy = new EventReportStrategy();

        ReportGenerator reportGenerator = new ReportGenerator(activityReportStrategy);

        // Commands
        Command turnOfTv1 = new TurnOffDeviceAction(tv1, josef);
        Command turnOfTv2 = new TurnOffDeviceAction(tv2, nadya);
        Command turnOfTv3 = new TurnOffDeviceAction(tv3, josef);
        Command turnOfTv4 = new TurnOffDeviceAction(tv4, nadya);
        Command turnOfTv5 = new TurnOffDeviceAction(tv5, josef);
        Command turnOfTv6 = new TurnOffDeviceAction(tv6, josef);

        Command turnOfStove1 = new TurnOffDeviceAction(stove1, nadya);
        Command turnOfStove2 = new TurnOffDeviceAction(stove2, nadya);

        Command loadProductMeat = new LoadProductAction((Fridge) fridge1, nadya, new Product("Meat"));
        Command loadProductMilk = new LoadProductAction((Fridge) fridge1, nadya, new Product("Milk"));
        Command loadProductBread = new LoadProductAction((Fridge) fridge1, nadya, new Product("Bread"));
        Command loadProductBread2 = new LoadProductAction((Fridge) fridge2, sveta, new Product("Bread"));

        Command turnOffComputer1 = new TurnOffDeviceAction(computerMother, josef);
        Command turnOffComputer2 = new TurnOffDeviceAction(computerMia, josef);
        Command turnOffComputer3 = new TurnOffDeviceAction(computerFather, josef);
        Command turnOffComputer4 = new TurnOffDeviceAction(computerOscar, josef);
        Command turnOffComputer5 = new TurnOffDeviceAction(computerMax, josef);
        Command turnOffComputer6 = new TurnOffDeviceAction(computerSophia, josef);

        Command turnOnComputer = new TurnOnDeviceAction(computerFather, josef);

        Command turnOnStove = new TurnOnDeviceAction(stove1, nadya);

        Command checkKitchenTemperature = new CheckTemperature(kitchenSensor, heatingSystemObserver);
        Command checkLivingRoomTemperature = new CheckTemperature(livingRoomSensor, heatingSystemObserver);
        Command checkDinningRoomTemperature = new CheckTemperature(dinningRoomSensor, heatingSystemObserver);
        Command checkParentsRoomTemperature = new CheckTemperature(parentsRoomSensor, heatingSystemObserver);
        Command checkOscarRoomTemperature = new CheckTemperature(oscarRoomSensor, heatingSystemObserver);
        Command checkMiaRoomTemperature = new CheckTemperature(miaRoomSensor, heatingSystemObserver);
        Command checkMaxRoomTemperature = new CheckTemperature(maxRoomSensor, heatingSystemObserver);
        Command checkSophiaRoomTemperature = new CheckTemperature(sophiaRoomSensor, heatingSystemObserver);
        Command checkOfficeTemperature = new CheckTemperature(officeSensor, heatingSystemObserver);

        Command turnOnTv = new TurnOnDeviceAction(tv1, vasya);

        // Execute activityReportStrategy
        DeviceApi api = new DeviceApi(reportGenerator);

        api.setAction(turnOfTv1);
        api.executeAction();

        api.setAction(turnOfTv2);
        api.executeAction();

        api.setAction(turnOfTv3);
        api.executeAction();

        api.setAction(turnOfTv4);
        api.executeAction();

        api.setAction(turnOfTv5);
        api.executeAction();

        api.setAction(turnOfTv6);
        api.executeAction();

        api.setAction(turnOfStove1);
        api.executeAction();

        api.setAction(turnOfStove2);
        api.executeAction();

        api.setAction(loadProductMeat);
        api.executeAction();

        api.setAction(loadProductMilk);
        api.executeAction();

        api.setAction(loadProductBread);
        api.executeAction();

        api.setAction(loadProductBread2);
        api.executeAction();

        api.setAction(turnOffComputer1);
        api.executeAction();

        api.setAction(turnOffComputer2);
        api.executeAction();

        api.setAction(turnOffComputer3);
        api.executeAction();

        api.setAction(turnOffComputer4);
        api.executeAction();

        api.setAction(turnOffComputer5);
        api.executeAction();

        api.setAction(turnOffComputer6);
        api.executeAction();

        api.setAction(turnOnComputer);
        api.executeAction();

        api.setAction(turnOnStove);
        api.executeAction();

        api.setAction(checkKitchenTemperature);
        api.executeAction();

        api.setAction(checkLivingRoomTemperature);
        api.executeAction();

        api.setAction(checkDinningRoomTemperature);
        api.executeAction();

        api.setAction(checkParentsRoomTemperature);
        api.executeAction();

        api.setAction(checkOscarRoomTemperature);
        api.executeAction();

        api.setAction(checkMiaRoomTemperature);
        api.executeAction();

        api.setAction(checkMaxRoomTemperature);
        api.executeAction();

        api.setAction(checkSophiaRoomTemperature);
        api.executeAction();

        api.setAction(checkOfficeTemperature);
        api.executeAction();

        api.setAction(turnOnTv);
        api.executeAction();

        System.out.println(reportGenerator.generateReport());
        reportGenerator.setReportStrategy(eventReportStrategy);
        System.out.println(reportGenerator.generateReport());

        // Execute consumptionReportStrategy
        reportGenerator.setReportStrategy(consumptionReportStrategy);

        // warnings ///////////////////////////////////////////////////

        Thread.sleep(1000); // 1 sec

        ///////////////////////////////////////////////////////////////

        System.out.println(reportGenerator.generateReport());

        // report houseConfigurationReport
        System.out.println(houseConfigurationReport.generateReport());
    }
}
