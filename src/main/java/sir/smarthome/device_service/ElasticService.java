package sir.smarthome.device_service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

/**
 * Service for Elasticsearch operations.
 * Manages index creation and document indexing.
 */
public class ElasticService {

    private final ElasticsearchClient client;

    public ElasticService() {
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        this.client = new ElasticsearchClient(transport);
    }

    /**
     * Creates new index if it doesn't exist
     * @param indexName Name of index to create
     * @throws IOException if Elasticsearch communication fails
     */
    public void createIndexIfNotExists(String indexName) throws IOException {
        boolean exists = client.indices().exists(b -> b.index(indexName)).value();
        if (!exists) {
            CreateIndexResponse response = client.indices().create(
                    CreateIndexRequest.of(c -> c
                            .index(indexName)
                            .mappings(TypeMapping.of(m -> m
                                    .properties("name", p -> p.text(t -> t))
                                    .properties("type", p -> p.keyword(k -> k))
                                    .properties("status", p -> p.keyword(k -> k))
                                    .properties("roomId", p -> p.keyword(k -> k))
                            ))
                    )
            );
            System.out.println("📦 Created index: " + response.index());
        }
    }

    public ElasticsearchClient getClient() {
        return client;
    }
}
