package sir.smarthome.notification_service;

public interface NotificationSubject {
    void registerObserver(NotificationObserver observer);
    void removeObserver(NotificationObserver observer);
    void notifyObservers(String topic, String message);
}
