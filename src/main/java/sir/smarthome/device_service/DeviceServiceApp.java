package sir.smarthome.device_service;

import co.elastic.clients.elasticsearch.core.IndexRequest;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sir.smarthome.common.LoggingInterceptor;
import sir.smarthome.common.Product;
import sir.smarthome.device_service.builder.DeviceCreator;
import sir.smarthome.device_service.devices.DeviceType;
import sir.smarthome.device_service.factories.ComputerFactory;
import sir.smarthome.device_service.factories.DeviceFactory;
import sir.smarthome.device_service.service.DeviceService;
import sir.smarthome.device_service.devices.Device;
import sir.smarthome.rest.DeviceRestApi;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import sir.smarthome.DeviceRepository;
import sir.smarthome.device_service.commands.*;
import sir.smarthome.device_service.factories.*;
import sir.smarthome.device_service.kafka.SimpleKafkaProducer;
import sir.smarthome.elasticsearch.DeviceIndexer;


/**
 * Main application class for Device Service.
 * Provides interface for device management.
 */
public class DeviceServiceApp {

    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceApp.class);

    public static void main(String[] args) throws IOException {

        logger.info("=== SmartHome Device Service ===");

        DeviceFactory computerFactory = ComputerFactory.getInstance();
        DeviceFactory fridgeFactory = FridgeFactory.getInstance();
        DeviceFactory multimediaFactory = MultimediaFactory.getInstance();
        DeviceFactory stoveFactory = StoveFactory.getInstance();

        DeviceCreator deviceCreator = new DeviceCreator(
                computerFactory,
                fridgeFactory,
                multimediaFactory,
                stoveFactory
        );

        DeviceApi deviceApi = new DeviceApi();
        SimpleKafkaProducer producer = new SimpleKafkaProducer();
        DeviceIndexer indexer = new DeviceIndexer();
        DeviceRepository repository = new DeviceRepository();
        Cache<UUID, Device> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();

        DeviceService service = new DeviceService(
                deviceApi,
                producer,
                indexer,
                cache,
                repository
        );

        ElasticService elasticService = new ElasticService();
        elasticService.createIndexIfNotExists("devices");

        try {
            new DeviceRestApi(service).start();
        } catch (IOException e) {
            logger.error("Failed to start REST API: {}", e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);

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
                        logger.error("Exit...");
                        service.getProducer().close();
                        return;
                    }
                    case "create" -> {
                        String typeStr = parts[1];
                        String name = parts[2];
                        double power = Double.parseDouble(parts[3]);
                        UUID roomId = UUID.fromString(parts[4]);

                        try {
                            DeviceType type = DeviceType.valueOf(typeStr.toUpperCase());

                            LoggingInterceptor.log("DeviceServiceApp", "Creating device: " + name + " (" + type + "), power: " + power + ", roomId: " + roomId);

                            Device device = deviceCreator.create(name, power, type);
                            device.setRoomId(roomId);
                            service.registerDevice(device, roomId);

                            elasticService.getClient().index(IndexRequest.of(i -> i
                                    .index("devices")
                                    .id(device.getId().toString())
                                    .document(Map.of(
                                            "name", device.getName(),
                                            "type", device.getClass().getSimpleName(),
                                            "status", "OFF",
                                            "roomId", device.getRoomId().toString()
                                    ))
                            ));

                            logger.info("Created device: {} ({})", device.getId(), name);

                        } catch (IllegalArgumentException e) {
                            logger.warn("Unknown device type: '{}'. Valid types: COMPUTER, FRIDGE, TV, STOVE", typeStr);
                        }
                    }

                    case "list" -> {
                        LoggingInterceptor.log("DeviceServiceApp", "Listing all devices");
                        logger.info("List of devices:");
                        service.getDevices().forEach((id, device) ->
                                logger.info("{} | {} | {}", id, device.getName(), device.getClass().getSimpleName()));
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
                        logger.error("Unknown command. Use this instead: create, list, on, off, volup, voldown, load, exit");
                    }
                }
            } catch (Exception e) {
                LoggingInterceptor.log("DeviceServiceApp", "Error: " + e.getMessage());
                logger.error("Unexpected error: {}", e.getMessage(), e);
            }
        }
    }
}

