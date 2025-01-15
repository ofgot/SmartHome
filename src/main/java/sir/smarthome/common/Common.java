package sir.smarthome.common;

import java.util.Random;

public class Common {
    public static final int MIN_TEMPERATURE = 15;
    public static final int MAX_TEMPERATURE = 30;
    public static final int TEMPERATURE_BORDER_LINE = 22;

    public static int getTemperature() {
        Random random = new Random();
        int temperature = random.nextInt(MAX_TEMPERATURE - MIN_TEMPERATURE + 1) + MIN_TEMPERATURE;
        System.out.println("Temperature is " + temperature);
        return temperature;
    }
}
