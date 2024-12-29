package sir.smarthome.devices;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import sir.smarthome.observers.HeatingSystemObserver;
import sir.smarthome.observers.Observer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TemperatureSensorTests {

    @Test
    void testTurnOnAndOff() {
        TemperatureSensor sensor = new TemperatureSensor("Living Room Sensor", 50.0);

        sensor.turnOn();
        assertNotNull(sensor.getLastTurnOn(), "Sensor's lastTurnOn should not be null after turning on.");

        sensor.turnOff();
        assertTrue(sensor.getUsageDuration() >= 0, "Usage duration should be greater than or equal to 0.");
    }

    @Test
    void testSetTemperature() {
        TemperatureSensor sensor = new TemperatureSensor("Kitchen Sensor", 50.0);
        sensor.turnOn();

        sensor.setTemperature(22);
        assertEquals(22, sensor.getTemperature(), "Temperature should be updated to 22.");

        sensor.setTemperature(16);
        assertEquals(16, sensor.getTemperature(), "Temperature should be updated to 16.");
    }

    @Test
    void testAddAndRemoveObserver() {
        TemperatureSensor sensor = new TemperatureSensor("Bedroom Sensor", 50.0);
        Observer observer1 = new HeatingSystemObserver();
        Observer observer2 = new HeatingSystemObserver();

        sensor.addObserver(observer1);
        sensor.addObserver(observer2);

        assertEquals(2, sensor.getObservers().size(), "There should be two observers.");

        sensor.removeObserver(observer1);
        assertEquals(1, sensor.getObservers().size(), "There should be one observer after removal.");
    }

    @Test
    void testNotifyObservers() {
        TemperatureSensor sensor = new TemperatureSensor("Hallway Sensor", 50.0);
        HeatingSystemObserver observer1 = new HeatingSystemObserver();
        HeatingSystemObserver observer2 = new HeatingSystemObserver();

        sensor.addObserver(observer1);
        sensor.addObserver(observer2);
    }

    @Test
    void testObserverHeatingSystemControl() {
        TemperatureSensor sensor = new TemperatureSensor("Garage Sensor", 50.0);
        HeatingSystemObserver observer = new HeatingSystemObserver();

        sensor.addObserver(observer);

        // Simulate temperature changes
        sensor.setTemperature(22);
    }
}
