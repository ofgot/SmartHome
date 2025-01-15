package sir.smarthome;

import java.util.Scanner;

public class SmartHomeApp {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose configuration which you want to see (1 or 2): ");
        Integer in = scanner.nextInt();
        SmartHomeApplication smartHomeApplication = new SmartHomeApplication();
        smartHomeApplication.run(in);
    }
}
