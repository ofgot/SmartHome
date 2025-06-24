package sir.smarthome.notification_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sir.smarthome.notification_service.kafka.NotificationConsumer;

public class NotificationApp {

    private static final Logger logger = LoggerFactory.getLogger(NotificationApp.class);

    public static void main(String[] args) {
        NotificationService service = new NotificationService();

        service.registerObserver(new EmailNotifier());
        service.registerObserver(new SmsNotifier());

        new Thread(new NotificationConsumer(service)).start();

        logger.info("NotificationApp started");
    }
}
