package sir.smarthome.notification_service;

public class NotificationApp {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();

        // observer registration
        service.registerObserver(new EmailNotifier());
        service.registerObserver(new SmsNotifier());

        // messages emulation from Kafka
        service.onKafkaMessage("device-added", "New device: Fridge");
        service.onKafkaMessage("house-updated", "New floor added to house");
    }
}
