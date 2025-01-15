package sir.smarthome.observers;

import sir.smarthome.common.Common;

public class HeatingSystemObserver implements Observer {

    @Override
    public void update(int temperature) {
        if (temperature > Common.TEMPERATURE_BORDER_LINE) {
            turnOffHeatingSystem();
        } else {
            System.out.println("Heating system is on.");
        }
    }

    public void turnOffHeatingSystem() {
        System.out.println("Heating system turned off.");
    }

    @Override
    public String toString() {
        return "Heating System";
    }
}
