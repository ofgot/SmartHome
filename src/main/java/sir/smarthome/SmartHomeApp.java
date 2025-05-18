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

import java.util.UUID;
import java.util.logging.Logger;

public class SmartHomeApp {
    private static final Logger logger = Logger.getLogger(SmartHomeApp.class.getName());


    public static void main(String[] args) throws InterruptedException {
        System.out.println("🚀 SmartHomeApp started");

        HouseServiceImpl houseService = new HouseServiceImpl();
        NotificationService notificationService = new NotificationService();
        notificationService.registerObserver(new EmailNotifier());
        notificationService.registerObserver(new SmsNotifier());

        UUID buildingId = UUID.randomUUID();
        UUID roomId = UUID.randomUUID();
        int floorNumber = 1;

        Building b = new Building(buildingId, "Main Building");
        Floor f = new Floor("Floor", floorNumber);
        Room r = new Room(roomId, "Living Room", buildingId, floorNumber);
        f.addRoom(r);
        b.addFloor(f);

        houseService.createBuilding(b);

        LoggingInterceptor.log("SmartHomeApp", "Created building " + buildingId);
        LoggingInterceptor.log("SmartHomeApp", "Created room " + roomId);

        // запускаем consumer — передаём ему существующий houseService
        new Thread(new DeviceEventConsumer(houseService, notificationService)).start();
        //notificationService.onKafkaMessage("device-added", "Fridge Samsung added");

        // Необязательная задержка, чтобы приложение не завершалось сразу
        Thread.sleep(10000);
    }
}
