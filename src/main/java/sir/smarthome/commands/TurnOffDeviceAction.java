package sir.smarthome.commands;

import sir.smarthome.devices.Device;
import sir.smarthome.residents.Resident;

public class TurnOffDeviceAction extends BaseAction<Device, Resident> {
    public TurnOffDeviceAction(Device receiver, Resident executor) {
        super(receiver, executor);
    }

    @Override
    public void execute() {
        System.out.println("Turning off device");
        receiver.turnOff();
    }

    @Override
    public String toString() {
        return "TurnOffDeviceAction by " + executor.getName() + " to: " + receiver.getName();
    }
}
