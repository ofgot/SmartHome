package sir.smarthome.commands;

import sir.smarthome.devices.TV;
import sir.smarthome.residents.Resident;

public class DecreaseVolumeAction extends BaseAction<TV, Resident> {
    int amount;

    public DecreaseVolumeAction(TV receiver, Resident executor, int amount) {
        super(receiver, executor);
        this.amount = amount;
    }

    @Override
    public void execute() {
        System.out.println("Decrease volume to " + amount);
        receiver.decreaseVolume(amount);
    }

    @Override
    public String toString() {
        return "IncreaseVolumeAction by " + executor.getName() + " to " + receiver.getName() + " with amount: " + amount;
    }
}
