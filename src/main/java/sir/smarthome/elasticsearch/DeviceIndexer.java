package sir.smarthome.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import sir.smarthome.device_service.devices.Device;

public class DeviceIndexer {
    private final ElasticsearchClient client = ElasticClientProvider.getClient();

    public void index(Device device) {
        try {
            IndexResponse response = client.index(i -> i
                    .index("devices")
                    .id(device.getId().toString())
                    .document(device)
            );
            System.out.println("Indexed in ES: " + device.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
