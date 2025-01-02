package sir.smarthome.common;

import java.util.Random;

public class Common {
    public static final int MINTEMPERATURE = 15;
    public static final int MAXTEMPERATURE = 30;

    public static int getTemperature() {
        Random random = new Random();
        int temperature = random.nextInt(MAXTEMPERATURE - MINTEMPERATURE + 1) + MINTEMPERATURE;
        System.out.println("Temperature is " + temperature);
        return temperature;
    }
}
