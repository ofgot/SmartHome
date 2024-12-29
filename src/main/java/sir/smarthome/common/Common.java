package sir.smarthome.common;

import java.util.Random;

public class Common {
    public static final int MINTEMPERATURE = -50;
    public static final int MAXTEMPERATURE = 30;

    public static int getTemperature() {
        Random random = new Random();
        return random.nextInt(MAXTEMPERATURE - MINTEMPERATURE + 1) + MINTEMPERATURE;
    }
}
