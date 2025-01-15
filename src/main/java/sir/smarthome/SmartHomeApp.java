package sir.smarthome;

import java.util.Scanner;
import java.util.logging.Logger;

public class SmartHomeApp {
    private static final Logger logger = Logger.getLogger(SmartHomeApp.class.getName());

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        logger.info("Choose configuration which you want to see (1 or 2): ");
        Integer in = scanner.nextInt();
        SmartHomeApplication smartHomeApplication = new SmartHomeApplication();
        smartHomeApplication.run(in);
    }
}
