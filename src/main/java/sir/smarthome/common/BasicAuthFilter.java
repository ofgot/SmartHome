package sir.smarthome.common;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.kafka.common.header.Headers;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * HTTP handler that implements Basic Authentication.
 * Validates credentials before passing requests to the next handler.
 */
public class BasicAuthFilter implements HttpHandler {
    private final HttpHandler next;
    private final String validUser = "admin";
    private final String validPassword = "admin123";

    public BasicAuthFilter(HttpHandler next) {
        this.next = next;
    }

    /**
     * Validates Basic Auth credentials against stored values
     * @param exchange HTTP exchange containing auth headers
     * @throws IOException if I/O error occurs during response
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String, List<String>> headers = exchange.getRequestHeaders();
        String auth = null;
        if (headers.containsKey("Authorization")) {
            auth = headers.get("Authorization").get(0);
        }

        if (auth != null && auth.startsWith("Basic ")) {
            String[] credentials = new String(Base64.getDecoder()
                    .decode(auth.substring(6))).split(":");

            if (credentials.length == 2 && credentials[0].equals(validUser) && credentials[1].equals(validPassword)) {
                next.handle(exchange);
                return;
            }
        }

        exchange.getResponseHeaders().set("WWW-Authenticate", "Basic realm=\"Access to SmartHome\"");
        exchange.sendResponseHeaders(401, -1);
    }
}

