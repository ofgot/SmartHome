package sir.smarthome.commands;

import sir.smarthome.common.Common;
import sir.smarthome.devices.TemperatureSensor;
import sir.smarthome.observers.HeatingSystemObserver;

/**
 * Represents an action to check and update the temperature using a {@link TemperatureSensor}.
 * This action retrieves the current temperature and updates the associated temperature sensor.
 */
public class CheckTemperature extends BaseAction<TemperatureSensor, HeatingSystemObserver> {

    /**
     * Constructs a new {@code CheckTemperature} action with the specified receiver and executor.
     *
     * @param receiver the {@link TemperatureSensor} that receives the updated temperature
     * @param executor the {@link HeatingSystemObserver} that observes the temperature check action
     */
    public CheckTemperature(TemperatureSensor receiver, HeatingSystemObserver executor) {
        super(receiver, executor);
    }

    /**
     * Executes the temperature check action.
     * <p>
     * Retrieves the current temperature from {@link Common#getTemperature()} and updates the receiver.
     * Prints a message indicating the action is being performed.
     */
    @Override
    public void execute() {
        System.out.println("Checking temperature");
        receiver.setTemperature(Common.getTemperature());
    }

    /**
     * Returns a string representation of the {@code CheckTemperature} action.
     * <p>
     * The string includes the executor and the name of the receiver.
     *
     * @return a string describing the action
     */
    @Override
    public String toString() {
        return "CheckTemperature action by " + executor + " for receiver: " + receiver.getName();
    }
}
