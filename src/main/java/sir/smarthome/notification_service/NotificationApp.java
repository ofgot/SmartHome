package sir.smarthome.notification_service;

import sir.smarthome.notification_service.kafka.NotificationConsumer;

public class NotificationApp {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();

        service.registerObserver(new EmailNotifier());
        service.registerObserver(new SmsNotifier());

        new Thread(new NotificationConsumer(service)).start();

        System.out.println("🚀 NotificationApp started");
    }
}
