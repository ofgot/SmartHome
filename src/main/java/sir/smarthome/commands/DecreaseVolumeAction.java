package sir.smarthome.commands;

import sir.smarthome.devices.TV;
import sir.smarthome.residents.Resident;

/**
 * Represents an action to decrease the volume of a {@link TV}.
 * This action is performed by a {@link Resident} and reduces the volume by a specified amount.
 */
public class DecreaseVolumeAction extends BaseAction<TV, Resident> {
    /**
     * The amount by which the volume will be decreased.
     */
    private final int amount;

    /**
     * Constructs a new {@code DecreaseVolumeAction} with the specified receiver, executor, and volume decrease amount.
     *
     * @param receiver the {@link TV} on which the volume decrease will be performed
     * @param executor the {@link Resident} who initiates the action
     * @param amount   the amount by which the volume will be decreased
     */
    public DecreaseVolumeAction(TV receiver, Resident executor, int amount) {
        super(receiver, executor);
        this.amount = amount;
    }

    /**
     * Executes the volume decrease action.
     * <p>
     * Decreases the volume of the {@link TV} by the specified amount and prints a message indicating the action.
     */
    @Override
    public void execute() {
        System.out.println("Decrease volume to " + amount);
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
        return "DecreaseVolumeAction by " + executor.getName() + " to " + receiver.getName() + " with amount: " + amount;
    }
}
