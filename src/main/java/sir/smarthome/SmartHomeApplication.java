package sir.smarthome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sir.smarthome.devices.Device;
import sir.smarthome.factories.DeviceFactory;
import sir.smarthome.factories.SensorFactory;

@SpringBootApplication
public class SmartHomeApplication {

    public static void main(String[] args) {

        DeviceFactory computerFactory = SensorFactory.getInstance();
        
        Device device = computerFactory.createDevice();

        System.out.println(device);
        
        
        
        
        SpringApplication.run(SmartHomeApplication.class, args);
    }

}
