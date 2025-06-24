package sir.smarthome.notification_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailNotifier implements NotificationObserver{

    private static final Logger logger = LoggerFactory.getLogger(EmailNotifier.class);

    @Override
    public void notify(String topic, String message) {
        logger.info("[EMAIL] " + topic + ": " + message);
    }
}
