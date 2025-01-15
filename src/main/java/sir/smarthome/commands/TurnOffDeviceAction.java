package sir.smarthome.commands;

import sir.smarthome.devices.Device;
import sir.smarthome.residents.Resident;

/**
 * Represents an action to turn off a {@link Device}.
 * This action is performed by a {@link Resident} who turns off the specified device.
 */
public class TurnOffDeviceAction extends BaseAction<Device, Resident> {

    /**
     * Constructs a new {@code TurnOffDeviceAction} with the specified receiver and executor.
     *
     * @param receiver the {@link Device} to be turned off
     * @param executor the {@link Resident} who initiates the action
     */
    public TurnOffDeviceAction(Device receiver, Resident executor) {
        super(receiver, executor);
    }

    /**
     * Executes the turn off action.
     * <p>
     * Turns off the specified device and prints a message indicating the action.
     */
    @Override
    public void execute() {
        System.out.println("Turning off device");
        receiver.turnOff();
    }

    /**
     * Returns a string representation of the {@code TurnOffDeviceAction}.
     * <p>
     * The string includes the executor's name and the receiver's name.
     *
     * @return a string describing the action
     */
    @Override
    public String toString() {
        return "TurnOffDeviceAction by " + executor.getName() + " to: " + receiver.getName();
    }
}
