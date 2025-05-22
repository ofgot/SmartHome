package sir.smarthome.device_service;

import co.elastic.clients.elasticsearch.core.IndexRequest;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import sir.smarthome.common.LoggingInterceptor;
import sir.smarthome.common.Product;
import sir.smarthome.device_service.controller.DeviceService;
import sir.smarthome.device_service.devices.Device;
import sir.smarthome.rest.DeviceRestApi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class DeviceServiceApp {


    public static void main(String[] args) throws IOException {
        DeviceService service = new DeviceService();
        ElasticService elasticService = new ElasticService();
        elasticService.createIndexIfNotExists("devices");

        try {
            new DeviceRestApi(service).start();
        } catch (IOException e) {
            System.err.println("Failed to start REST API: " + e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== SmartHome Device Service ===");

        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            try {
                String command = parts[0].toLowerCase();
                LoggingInterceptor.log("DeviceServiceApp", "Received command: " + input);

                switch (command) {
                    case "exit" -> {
                        LoggingInterceptor.log("DeviceServiceApp", "Exiting application");
                        System.out.println("Exit...");
                        service.getProducer().close();
                        return;
                    }
                    case "create" -> {
                        String type = parts[1];
                        String name = parts[2];
                        double power = Double.parseDouble(parts[3]);
                        UUID roomId = UUID.fromString(parts[4]);

                        LoggingInterceptor.log("DeviceServiceApp", "Creating device: " + name + " (" + type + "), power: " + power + ", roomId: " + roomId);

                        Device device = service.createDevice(name, power, type, roomId);
                        device.setRoomId(roomId);
                        elasticService.getClient().index(IndexRequest.of(i -> i
                                .index("devices")
                                .id(device.getId().toString())
                                .document(Map.of(
                                        "name", device.getName(),
                                        "type", device.getClass().getSimpleName(),
                                        "status", "OFF", // если нет поля — можешь убрать
                                        "roomId", device.getRoomId().toString()
                                ))
                        ));

                        System.out.println("Created device: " + device.getId() + " (" + name + ")");
                    }
                    case "list" -> {
                        LoggingInterceptor.log("DeviceServiceApp", "Listing all devices");
                        System.out.println("List of devices:");
                        service.getDevices().forEach((id, device) ->
                                System.out.println(id + " | " + device.getName() + " | " + device.getClass().getSimpleName()));
                    }
                    case "on" -> {
                        UUID id = UUID.fromString(parts[1]);
                        LoggingInterceptor.log("DeviceServiceApp", "Turning ON device: " + id);
                        service.turnOnDevice(id);
                    }
                    case "off" -> {
                        UUID id = UUID.fromString(parts[1]);
                        LoggingInterceptor.log("DeviceServiceApp", "Turning OFF device: " + id);
                        service.turnOffDevice(id);
                    }
                    case "volup" -> {
                        UUID id = UUID.fromString(parts[1]);
                        int step = Integer.parseInt(parts[2]);
                        LoggingInterceptor.log("DeviceServiceApp", "Increasing volume for device " + id + " by " + step);
                        service.increaseVolume(id, step);
                    }
                    case "voldown" -> {
                        UUID id = UUID.fromString(parts[1]);
                        int step = Integer.parseInt(parts[2]);
                        LoggingInterceptor.log("DeviceServiceApp", "Decreasing volume for device " + id + " by " + step);
                        service.decreaseVolume(id, step);
                    }
                    case "load" -> {
                        UUID id = UUID.fromString(parts[1]);
                        String productName = parts[2];
                        Product product = new Product(productName);
                        LoggingInterceptor.log("DeviceServiceApp", "Loading product " + productName + " into fridge " + id);
                        service.loadFridge(id, product);
                    }
                    default -> {
                        LoggingInterceptor.log("DeviceServiceApp", "Unknown command: " + command);
                        System.out.println("Unknown command. Use this instead: create, list, on, off, volup, voldown, load, exit");
                    }
                }
            } catch (Exception e) {
                LoggingInterceptor.log("DeviceServiceApp", "Error: " + e.getMessage());
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}

