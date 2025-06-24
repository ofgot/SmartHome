package sir.smarthome.notification_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sir.smarthome.common.LoggingInterceptor;

import java.util.ArrayList;
import java.util.List;

public class NotificationService implements NotificationSubject{

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final List<NotificationObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(NotificationObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String topic, String message) {
        for (NotificationObserver observer : observers) {
            observer.notify(topic, message);
            LoggingInterceptor.log("NotificationService", "Sending notification to observers: " + message);

        }
    }

    public void onKafkaMessage(String topic, String message) {
        logger.info("[Kafka] Received: {} → {}", topic, message);
        notifyObservers(topic, message);
    }
}
