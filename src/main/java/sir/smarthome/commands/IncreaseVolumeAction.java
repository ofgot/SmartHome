package sir.smarthome.commands;

import sir.smarthome.devices.TV;
import sir.smarthome.residents.Resident;

public class IncreaseVolumeAction extends BaseAction<TV, Resident>{
    private int amount;

    public IncreaseVolumeAction(TV receiver, Resident executor, int amount) {
        super(receiver, executor);
        this.amount = amount;
    }

    @Override
    public void execute() {
        System.out.println("Increase volume to " + amount);
        receiver.increaseVolume(amount);
    }

    @Override
    public String toString() {
        return "IncreaseVolumeAction by " + executor.getName() + " to " + receiver.getName() + " with amount: " + amount;
    }
}
