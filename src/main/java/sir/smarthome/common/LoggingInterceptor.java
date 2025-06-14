package sir.smarthome.common;

import java.time.LocalDateTime;

public class LoggingInterceptor {
    public static void log(String source, String message) {
        System.out.println("[" + LocalDateTime.now() + "] [" + source + "] " + message);
    }
}
