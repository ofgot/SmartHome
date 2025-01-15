package sir.smarthome.commands;

import sir.smarthome.devices.TV;
import sir.smarthome.residents.Resident;

/**
 * Represents an action to increase the volume of a {@link TV}.
 * This action is performed by a {@link Resident} and increases the volume by a specified amount.
 */
public class IncreaseVolumeAction extends BaseAction<TV, Resident> {

    /**
     * The amount by which the volume will be increased.
     */
    private final int amount;

    /**
     * Constructs a new {@code IncreaseVolumeAction} with the specified receiver, executor, and volume increase amount.
     *
     * @param receiver the {@link TV} on which the volume increase will be performed
     * @param executor the {@link Resident} who initiates the action
     * @param amount   the amount by which the volume will be increased
     */
    public IncreaseVolumeAction(TV receiver, Resident executor, int amount) {
        super(receiver, executor);
        this.amount = amount;
    }

    /**
     * Executes the volume increase action.
     * <p>
     * Increases the volume of the {@link TV} by the specified amount and prints a message indicating the action.
     */
    @Override
    public void execute() {
        System.out.println("Increase volume to " + amount);
        receiver.increaseVolume(amount);
    }

    /**
     * Returns a string representation of the {@code IncreaseVolumeAction}.
     * <p>
     * The string includes the executor's name, the receiver's name, and the increase amount.
     *
     * @return a string describing the action
     */
    @Override
    public String toString() {
        return "IncreaseVolumeAction by " + executor.getName() + " to " + receiver.getName() + " with amount: " + amount;
    }
}
