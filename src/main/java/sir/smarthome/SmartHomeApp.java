package sir.smarthome;

import sir.smarthome.common.LoggingInterceptor;
import sir.smarthome.house_service.HouseServiceImpl;
import sir.smarthome.house_service.kafka.DeviceEventConsumer;
import sir.smarthome.house_service.model.Building;
import sir.smarthome.house_service.model.Floor;
import sir.smarthome.house_service.model.Room;
import sir.smarthome.notification_service.EmailNotifier;
import sir.smarthome.notification_service.NotificationService;
import sir.smarthome.notification_service.SmsNotifier;
import sir.smarthome.rest.HouseRestApi;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;


/**
 * Main application class for SmartHome system.
 * Initializes building structure and services.
 */public class SmartHomeApp {
    private static final Logger logger = Logger.getLogger(SmartHomeApp.class.getName());


    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("🚀 SmartHomeApp started");

        HouseServiceImpl houseService = new HouseServiceImpl();
        NotificationService notificationService = new NotificationService();
        notificationService.registerObserver(new EmailNotifier());
        notificationService.registerObserver(new SmsNotifier());

        UUID buildingId = UUID.randomUUID();
        Building building = new Building(buildingId, "Main Smart Home");

        for (int floorNumber = 1; floorNumber <= 2; floorNumber++) {
            Floor floor = new Floor("Floor " + floorNumber, floorNumber);

            for (int i = 1; i <= 3; i++) {
                UUID roomId = UUID.randomUUID();
                Room room = new Room(roomId, "Room " + i + "F" + floorNumber, buildingId, floorNumber);
                floor.addRoom(room);
                logger.info(String.format("Created room %s on floor %d", roomId, floorNumber));
            }

            building.addFloor(floor);
            logger.info(String.format("Added floor %d to building %s", floorNumber, buildingId));
        }

        houseService.createBuilding(building);
        logger.info("Created building " + buildingId);

        new Thread(new DeviceEventConsumer(houseService, notificationService)).start();
        HouseRestApi houseApi = new HouseRestApi(houseService);
        houseApi.start();
        Thread.sleep(10000);
    }
}
