package sir.smarthome.house_service.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import sir.smarthome.common.DeviceEventDTO;
import sir.smarthome.common.LoggingInterceptor;
import sir.smarthome.house_service.HouseServiceImpl;
import sir.smarthome.house_service.model.Room;
import sir.smarthome.notification_service.NotificationService;

import java.time.Duration;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.UUID;

/**
 * Kafka consumer for processing device events.
 * Updates room-device associations and triggers notifications.
 */
public class DeviceEventConsumer implements Runnable {

    private final KafkaConsumer<String, String> consumer;
    private final ObjectMapper mapper = new ObjectMapper();
    private final HouseServiceImpl houseService;
    private final NotificationService notificationService;

    public DeviceEventConsumer(HouseServiceImpl houseService, NotificationService notificationService) {
        this.houseService = houseService;
        this.notificationService = notificationService;
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "house-service-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        this.consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("device-events"));
    }

    @Override
    public void run() {
        LoggingInterceptor.log("DeviceEventConsumer", "👂 Consumer started — waiting for messages...");
        while (true) {
            var records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                try {
                    DeviceEventDTO event = mapper.readValue(record.value(), DeviceEventDTO.class);
                    UUID roomId = event.getRoomId();
                    UUID deviceId = event.getId();

                    LoggingInterceptor.log("DeviceEventConsumer", "Received event for device: " + deviceId + " → Room: " + roomId);

                    try {
                        Room room = houseService.getRoomById(roomId);
                        room.addDevice(deviceId);
                        String message = "Device " + event.getName() + " of type " + event.getType() + " added to room " + roomId;
                        notificationService.notifyObservers("device-added", message);
                        LoggingInterceptor.log("DeviceEventConsumer", "🔔 Sending to observers: " + message);



                    } catch (NoSuchElementException e) {
                        LoggingInterceptor.log("DeviceEventConsumer", "❌ Room not found: " + roomId);
                    }
                } catch (Exception e) {
                    LoggingInterceptor.log("DeviceEventConsumer", "⚠️ Failed to process record: " + e.getMessage());
                }
            }
        }
    }
}
