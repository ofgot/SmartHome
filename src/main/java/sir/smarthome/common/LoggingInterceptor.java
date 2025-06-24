package sir.smarthome.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    public static void log(String source, String message) {
        logger.info("[{}] {}", source, message);
    }
}

