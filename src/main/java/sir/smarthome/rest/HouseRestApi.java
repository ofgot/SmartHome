package sir.smarthome.rest;
import sir.smarthome.common.BasicAuthFilter;
import sir.smarthome.house_service.HouseService;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import sir.smarthome.house_service.model.Building;
import sir.smarthome.house_service.model.Floor;
import sir.smarthome.house_service.model.Room;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST API for managing house structure (buildings, floors, rooms).
 * Exposes endpoints for retrieving house hierarchy and room information.
 */
public class HouseRestApi {
    private final HouseService houseService;

    public HouseRestApi(HouseService houseService) {
        this.houseService = houseService;
    }

    /**
     * Starts the HTTP server on port 8090 and configures endpoints:
     * - GET /house - Full house structure with buildings/floors/rooms
     * - GET /rooms - List of all rooms
     * - GET /room?id={uuid} - Single room details
     * @throws IOException if server fails to start
     */
    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8090), 0);

        server.createContext("/house", exchange -> {
            if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                sendResponse(exchange, 405, "Method Not Allowed");
                return;
            }

            List<Building> buildings = houseService.listBuildings();
            StringBuilder sb = new StringBuilder();
            sb.append("[\n");
            for (Building b : buildings) {
                sb.append(String.format("  {\"id\": \"%s\", \"name\": \"%s\", \"floors\": [", b.getId(), b.getName()));
                for (Floor f : b.getFloors()) {
                    sb.append(String.format("{\"number\": %d, \"rooms\": [", f.getNumber()));
                    for (Room r : f.getRooms()) {
                        sb.append(String.format("{\"id\": \"%s\", \"name\": \"%s\"},", r.getId(), r.getName()));
                    }
                    if (!f.getRooms().isEmpty()) sb.setLength(sb.length() - 1);
                    sb.append("]}, ");
                }
                if (!b.getFloors().isEmpty()) sb.setLength(sb.length() - 2);
                sb.append("]},\n");
            }
            if (!buildings.isEmpty()) sb.setLength(sb.length() - 2);
            sb.append("\n]");

            sendResponse(exchange, 200, sb.toString());
        });

        server.createContext("/rooms", exchange -> {
            if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                sendResponse(exchange, 405, "Method Not Allowed");
                return;
            }

            List<Room> allRooms = new ArrayList<>();
            List<Building> buildings = houseService.listBuildings();

            for (Building b : buildings) {
                allRooms.addAll(houseService.listAllRooms(b.getId()));
            }

            String json = allRooms.stream()
                    .map(r -> String.format(
                            "{\"id\": \"%s\", \"name\": \"%s\", \"buildingId\": \"%s\", \"floor\": %d}",
                            r.getId(), r.getName(), r.getBuildingId(), r.getFloorNumber()))
                    .collect(Collectors.joining(",\n", "[\n", "\n]"));

            sendResponse(exchange, 200, json);
        });

        server.createContext("/room", exchange -> {
            if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                sendResponse(exchange, 405, "Method Not Allowed");
                return;
            }

            String query = exchange.getRequestURI().getQuery(); // ?id=UUID
            if (query == null || !query.startsWith("id=")) {
                sendResponse(exchange, 400, "Missing id param");
                return;
            }

            UUID roomId;
            try {
                roomId = UUID.fromString(query.substring(3));
            } catch (IllegalArgumentException e) {
                sendResponse(exchange, 400, "Invalid UUID");
                return;
            }

            Room room = houseService.getRoomById(roomId);
            if (room == null) {
                sendResponse(exchange, 404, "Room not found");
            } else {
                String json = String.format(
                        "{ \"id\": \"%s\", \"name\": \"%s\", \"buildingId\": \"%s\", \"floorNumber\": %d }",
                        room.getId(), room.getName(), room.getBuildingId(), room.getFloorNumber()
                );
                sendResponse(exchange, 200, json);
            }
        });

        server.setExecutor(null);
        server.start();
        System.out.println("House REST API started at http://localhost:8090");
    }

    /**
     * Helper method to send HTTP responses
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
