package sir.smarthome.commands;

import sir.smarthome.devices.Device;
import sir.smarthome.residents.Resident;

public class TurnOnDeviceAction extends BaseAction<Device, Resident> {
    public TurnOnDeviceAction(Device receiver, Resident executor) {
        super(receiver, executor);
    }

    @Override
    public void execute() {
        System.out.println("Turning on device");
        receiver.turnOn();
    }

    @Override
    public String toString() {
        return "TurnOnDeviceAction by " + executor.getName() + " to: " + receiver.getName();
    }
}
