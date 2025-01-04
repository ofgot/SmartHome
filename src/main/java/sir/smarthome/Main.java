package sir.smarthome;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Integer in = scanner.nextInt();
        SmartHomeApplication smartHomeApplication = new SmartHomeApplication();
        smartHomeApplication.run(in);
    }
}
