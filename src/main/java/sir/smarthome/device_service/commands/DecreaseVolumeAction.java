package sir.smarthome.device_service.commands;

import sir.smarthome.device_service.devices.TV;

import java.util.logging.Logger;

/**
 * Represents an action to decrease the volume of a {@link TV}.
 */
public class DecreaseVolumeAction extends BaseAction<TV> {
    private static final Logger logger = Logger.getLogger(DecreaseVolumeAction.class.getName());
    /**
     * The amount by which the volume will be decreased.
     */
    private final int amount;

    /**
     * Constructs a new {@code DecreaseVolumeAction} with the specified receiver, executor, and volume decrease amount.
     *
     * @param receiver the {@link TV} on which the volume decrease will be performed
     * @param amount   the amount by which the volume will be decreased
     */
    public DecreaseVolumeAction(TV receiver, int amount) {
        super(receiver);
        this.amount = amount;
    }

    /**
     * Executes the volume decrease action.
     * <p>
     * Decreases the volume of the {@link TV} by the specified amount and prints a message indicating the action.
     */
    @Override
    public void execute() {
        logger.info("Decrease volume to " + amount);
        receiver.decreaseVolume(amount);
    }

    /**
     * Returns a string representation of the {@code DecreaseVolumeAction}.
     * <p>
     * The string includes the executor's name, the receiver's name, and the decrease amount.
     *
     * @return a string describing the action
     */
    @Override
    public String toString() {
        return "DecreaseVolumeAction " + "to " + receiver.getName() + " with amount: " + amount;
    }
}
