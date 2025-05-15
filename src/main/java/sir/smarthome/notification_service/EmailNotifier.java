package sir.smarthome.notification_service;

public class EmailNotifier implements NotificationObserver{
    @Override
    public void notify(String topic, String message) {
        System.out.println("[EMAIL] " + topic + ": " + message);
    }
}
