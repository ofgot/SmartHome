package sir.smarthome.notification_service;

public class SmsNotifier implements NotificationObserver{
    @Override
    public void notify(String topic, String message) {
        System.out.println("[SMS] " + topic + ": " + message);
    }
}
