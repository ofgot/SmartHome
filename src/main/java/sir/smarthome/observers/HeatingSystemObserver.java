package sir.smarthome.observers;

public class HeatingSystemObserver implements Observer {
    private int temperature;

    @Override
    public void update(int temperature) {
        this.temperature = temperature;
        if (this.temperature > 22) {
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
