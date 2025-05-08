package sir.smarthome.commands;

import sir.smarthome.device_service.devices.TV;

import java.util.logging.Logger;

        /**
         * Represents an action to increase the volume of a {@link TV}.
         */
        public class IncreaseVolumeAction extends BaseAction<TV> {
            private static final Logger logger = Logger.getLogger(IncreaseVolumeAction.class.getName());
            /**
             * The amount by which the volume will be increased.
             */
            private final int amount;

            /**
             * Constructs a new {@code IncreaseVolumeAction} with the specified receiver, executor, and volume increase amount.
             *
             * @param receiver the {@link TV} on which the volume increase will be performed
             * @param amount   the amount by which the volume will be increased
             */
            public IncreaseVolumeAction(TV receiver, int amount) {
                super(receiver);
                this.amount = amount;
            }

            /**
             * Executes the volume increase action.
             * <p>
             * Increases the volume of the {@link TV} by the specified amount and prints a message indicating the action.
             */
            @Override
            public void execute() {
                logger.info("Increase volume to " + amount);
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
                return "IncreaseVolumeAction " + " to " + receiver.getName() + " with amount: " + amount;
            }
        }
