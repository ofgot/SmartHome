package sir.smarthome.commands;

import sir.smarthome.devices.Device;
import sir.smarthome.residents.Resident;

/**
 * Represents an action to turn on a {@link Device}.
 * This action is performed by a {@link Resident} who turns on the specified device.
 */
public class TurnOnDeviceAction extends BaseAction<Device, Resident> {

    /**
     * Constructs a new {@code TurnOnDeviceAction} with the specified receiver and executor.
     *
     * @param receiver the {@link Device} to be turned on
     * @param executor the {@link Resident} who initiates the action
     */
    public TurnOnDeviceAction(Device receiver, Resident executor) {
        super(receiver, executor);
    }

    /**
     * Executes the turn on action.
     * <p>
     * Turns on the specified device and prints a message indicating the action.
     */
    @Override
    public void execute() {
        System.out.println("Turning on device");
        receiver.turnOn();
    }

    /**
     * Returns a string representation of the {@code TurnOnDeviceAction}.
     * <p>
     * The string includes the executor's name and the receiver's name.
     *
     * @return a string describing the action
     */
    @Override
    public String toString() {
        return "TurnOnDeviceAction by " + executor.getName() + " to: " + receiver.getName();
    }
}
