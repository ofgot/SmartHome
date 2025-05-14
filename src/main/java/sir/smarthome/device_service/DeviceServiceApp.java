package sir.smarthome.device_service;

import sir.smarthome.common.Product;
import sir.smarthome.device_service.controller.DeviceService;
import sir.smarthome.device_service.devices.Device;

import java.util.Scanner;
import java.util.UUID;

public class DeviceServiceApp {

    public static void main(String[] args) {
        DeviceService service = new DeviceService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SmartHome Device Service ===");

        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            try {
                switch (parts[0].toLowerCase()) {
                    case "exit" -> {
                        System.out.println("Exit...");
                        return;
                    }
                    case "create" -> {
                        String type = parts[1];
                        String name = parts[2];
                        double power = Double.parseDouble(parts[3]);
                        Device device = service.createDevice(name, power, type);
                        System.out.println("Created device: " + device.getId() + " (" + name + ")");
                    }
                    case "list" -> {
                        System.out.println("List of devices:");
                        service.getDevices().forEach((id, device) ->
                                System.out.println(id + " | " + device.getName() + " | " + device.getClass().getSimpleName()));
                    }
                    case "on" -> {
                        UUID id = UUID.fromString(parts[1]);
                        service.turnOnDevice(id);
                    }
                    case "off" -> {
                        UUID id = UUID.fromString(parts[1]);
                        service.turnOffDevice(id);
                    }
                    case "volup" -> {
                        UUID id = UUID.fromString(parts[1]);
                        int step = Integer.parseInt(parts[2]);
                        service.increaseVolume(id, step);
                    }
                    case "voldown" -> {
                        UUID id = UUID.fromString(parts[1]);
                        int step = Integer.parseInt(parts[2]);
                        service.decreaseVolume(id, step);
                    }
                    case "load" -> {
                        UUID id = UUID.fromString(parts[1]);
                        String productName = parts[2];
                        Product product = new Product(productName);
                        service.loadFridge(id, product);
                    }
                    default -> System.out.println("Unknown command. Use this instead: create, list, on, off, volup, voldown, load, exit");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}

