package sir.smarthome.commands;

import sir.smarthome.common.Common;
import sir.smarthome.devices.TemperatureSensor;
import sir.smarthome.observers.HeatingSystemObserver;

public class CheckTemperature extends BaseAction<TemperatureSensor, HeatingSystemObserver> {

    public CheckTemperature(TemperatureSensor receiver, HeatingSystemObserver executor) {
        super(receiver, executor);
    }

    @Override
    public void execute() {
        System.out.println("Checking temperature");
        receiver.setTemperature(Common.getTemperature());
    }

    @Override
    public String toString() {
        return "CheckTemperature action by " + executor + " for receiver: " + receiver.getName();
    }
}
