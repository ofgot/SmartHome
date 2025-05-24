package sir.smarthome.notification_service;

public interface NotificationObserver {
    void notify(String topic, String message);
}
