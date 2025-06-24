package sir.smarthome.device_service.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sir.smarthome.common.DeviceEventDTO;
import sir.smarthome.device_service.DeviceServiceApp;

import java.util.Properties;

/**
 * Kafka producer for sending device events.
 * Handles JSON serialization and message publishing.
 */
public class SimpleKafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(SimpleKafkaProducer.class);

    private final KafkaProducer<String, String> producer;
    private final ObjectMapper objectMapper;

    public SimpleKafkaProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        this.producer = new KafkaProducer<>(props);
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Sends device event to Kafka topic
     * @param event Device event data to publish
     */
    public void sendDeviceEvent(DeviceEventDTO event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            ProducerRecord<String, String> record = new ProducerRecord<>("device-events", json);
            producer.send(record);
            logger.info("[Kafka] Sent: " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes Kafka producer resources
     */
    public void close() {
        producer.close();
    }
}
