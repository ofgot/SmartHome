package sir.smarthome.observers;

public class HeatingSystemObserver implements Observer {
    private int temperature;

    @Override
    public void update(int temperature) {
        this.temperature = temperature;
        if (temperature > 20) { //TODO: make dynamic
            turnOffHeatingSystem();
        } else {
            System.out.println("Heating system is on.");
        }
    }

    public void turnOffHeatingSystem() {
        System.out.println("Heating system turned off.");
    }
}
