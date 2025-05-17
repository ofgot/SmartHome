package sir.smarthome.notification_service;

import java.util.ArrayList;
import java.util.List;

public class NotificationService implements NotificationSubject{
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
        }
    }

    public void onKafkaMessage(String topic, String message) {
        System.out.println("[Kafka] Received: " + topic + " → " + message);
        notifyObservers(topic, message);
    }
}
