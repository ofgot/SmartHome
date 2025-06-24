package sir.smarthome.notification_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsNotifier implements NotificationObserver{

    private static final Logger logger = LoggerFactory.getLogger(SmsNotifier.class);

    @Override
    public void notify(String topic, String message) {
        logger.info("[SMS] " + topic + ": " + message);
    }
}
