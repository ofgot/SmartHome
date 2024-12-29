package sir.smarthome.commands;

import sir.smarthome.common.Common;
import sir.smarthome.devices.TemperatureSensor;
import sir.smarthome.observers.HeatingSystemObserver;

public class CheckTemperature extends BaseAction<HeatingSystemObserver, TemperatureSensor>{

    public CheckTemperature(HeatingSystemObserver receiver, TemperatureSensor executor) {
        super(receiver, executor);
    }

    @Override
    public void execute() {
        System.out.println("Checking temperature");
        executor.setTemperature(Common.getTemperature());
    }
}
