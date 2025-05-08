package sir.smarthome.device_service.commands;

import sir.smarthome.device_service.devices.Device;

import java.util.logging.Logger;

/**
 * Represents an action to turn on a {@link Device}.
 */
public class TurnOnDeviceAction extends BaseAction<Device> {
    private static final Logger logger = Logger.getLogger(TurnOnDeviceAction.class.getName());

    /**
     * Constructs a new {@code TurnOnDeviceAction} with the specified receiver and executor.
     *
     * @param receiver the {@link Device} to be turned on
     */
    public TurnOnDeviceAction(Device receiver) {
        super(receiver);
    }

    /**
     * Executes the turn on action.
     * <p>
     * Turns on the specified device and prints a message indicating the action.
     */
    @Override
    public void execute() {
        logger.info("Turning on device");
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
        return "TurnOnDeviceAction to: " + receiver.getName();
    }
}
