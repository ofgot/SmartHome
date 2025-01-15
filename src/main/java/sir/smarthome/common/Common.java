package sir.smarthome.common;

import java.util.Random;
import java.util.logging.Logger;

public class Common {
    private static final Logger logger = Logger.getLogger(Common.class.getName());
    public static final int MIN_TEMPERATURE = 15;
    public static final int MAX_TEMPERATURE = 30;
    public static final int TEMPERATURE_BORDER_LINE = 22;

    public static int getTemperature() {
        Random random = new Random();
        int temperature = random.nextInt(MAX_TEMPERATURE - MIN_TEMPERATURE + 1) + MIN_TEMPERATURE;
        logger.info("Temperature is " + temperature);
        return temperature;
    }
}
