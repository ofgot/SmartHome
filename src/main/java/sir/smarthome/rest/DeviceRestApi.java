package sir.smarthome.rest;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import sir.smarthome.common.BasicAuthFilter;
import sir.smarthome.device_service.service.DeviceService;
import sir.smarthome.device_service.devices.Device;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * REST API controller for smart home device management.
 * Provides endpoints for retrieving device information and status.
 * All endpoints except /status require Basic Authentication.
 */
public class DeviceRestApi {
    private final DeviceService service;

    public DeviceRestApi(DeviceService service) {
        this.service = service;
    }

    /**
     * Starts the HTTP server on port 8080 and registers endpoints:
     * GET /status - Service health check
     * GET /devices - List all devices (authenticated)
     * GET /device?id={uuid} - Get specific device (authenticated)
     * @throws IOException if the HTTP server fails to start
     */
    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/status", exchange -> {
            sendResponse(exchange, 200, "SmartHome API is running");
        });

        server.createContext("/devices", new BasicAuthFilter(exchange -> {
            if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                sendResponse(exchange, 405, "Method Not Allowed");
                return;
            }

            StringBuilder response = new StringBuilder();
            response.append("[\n");
            for (var entry : service.getDevices().entrySet()) {
                Device d = entry.getValue();
                response.append(String.format("  { \"id\": \"%s\", \"name\": \"%s\", \"type\": \"%s\" },\n",
                        d.getId(), d.getName(), d.getClass().getSimpleName()));
            }
            if (response.length() > 2) response.setLength(response.length() - 2);
            response.append("\n]");

            sendResponse(exchange, 200, response.toString());
        }));

        server.createContext("/device", new BasicAuthFilter(new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                    sendResponse(exchange, 405, "Method Not Allowed");
                    return;
                }

                String query = exchange.getRequestURI().getQuery(); // ?id=xxx
                if (query == null || !query.startsWith("id=")) {
                    sendResponse(exchange, 400, "Missing id param");
                    return;
                }

                String idStr = query.substring(3);
                try {
                    UUID id = UUID.fromString(idStr);
                    Device d = service.getDeviceById(id);
                    if (d != null) {
                        String json = String.format("{ \"id\": \"%s\", \"name\": \"%s\", \"type\": \"%s\" }",
                                d.getId(), d.getName(), d.getClass().getSimpleName());
                        sendResponse(exchange, 200, json);
                    } else {
                        sendResponse(exchange, 404, "Device not found");
                    }
                } catch (IllegalArgumentException e) {
                    sendResponse(exchange, 400, "Invalid UUID");
                }
            }
        }));

        server.setExecutor(null);
        server.start();
        System.out.println("REST API started on http://localhost:8080");
    }

    /**
     * Sends HTTP response with given status and body
     * @param exchange HTTP exchange object
     * @param statusCode HTTP status code
     * @param response Response body content
     * @throws IOException if I/O error occurs
     */
    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}