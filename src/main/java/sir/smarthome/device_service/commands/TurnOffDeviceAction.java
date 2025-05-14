package sir.smarthome.device_service.commands;

import sir.smarthome.device_service.devices.Device;

import java.util.logging.Logger;

/**
 * Represents an action to turn off a {@link Device}.
 */
public class TurnOffDeviceAction extends BaseAction<Device> {
    private static final Logger logger = Logger.getLogger(TurnOffDeviceAction.class.getName());

    /**
     * Constructs a new {@code TurnOffDeviceAction} with the specified receiver and executor.
     *
     * @param receiver the {@link Device} to be turned off
     */
    public TurnOffDeviceAction(Device receiver) {
        super(receiver);
    }

    /**
     * Executes the turn-off action.
     * <p>
     * Turns off the specified device and prints a message indicating the action.
     */
    @Override
    public void execute() {
        logger.info("Turning off device");
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
        return "TurnOffDeviceAction to: " + receiver.getName();
    }
}
