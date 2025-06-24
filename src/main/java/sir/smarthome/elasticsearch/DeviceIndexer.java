package sir.smarthome.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sir.smarthome.device_service.DeviceServiceApp;
import sir.smarthome.device_service.devices.Device;

/**
 * Component for indexing devices in Elasticsearch.
 * Handles document creation and updates.
 */
public class DeviceIndexer {

    private static final Logger logger = LoggerFactory.getLogger(DeviceIndexer.class);

    private final ElasticsearchClient client = ElasticClientProvider.getClient();
    public void index(Device device) {
        try {
            IndexResponse response = client.index(i -> i
                    .index("devices")
                    .id(device.getId().toString())
                    .document(device)
            );
            logger.info("Indexed in ES: " + device.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
