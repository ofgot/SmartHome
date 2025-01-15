package sir.smarthome.devices;

import java.io.DataOutput;
import java.util.*;

public class TV extends Device {
    public TV(String name, double powerConsumption) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.powerConsumption = powerConsumption;
        this.usageDuration = 0;
        this.condition = 100;
    }

    public void changeChannel() {
        System.out.println("Channel changed.");
    }

    public void increaseVolume(int increment) {
        System.out.println("Volume increased by " + increment);
    }

    public void decreaseVolume(int decrement) {
        System.out.println("Volume decreased by " + decrement);
    }
}