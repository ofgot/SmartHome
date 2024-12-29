package sir.smarthome.observers;

public class HeatingSystemObserver implements Observer {
    private int temperature;

    @Override
    public void update(int temperature) {
        this.temperature = temperature;
        if (this.temperature > 25) {
            turnOffHeatingSystem();
        } else {
            System.out.println("Heating system is on.");
        }
    }

    public void turnOffHeatingSystem() {
        System.out.println("Heating system turned off.");
    }
}
