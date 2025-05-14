package sir.smarthome.device_service.reports;

import sir.smarthome.device_service.commands.Command;

import java.util.Date;

public class Event {
    private final String residentAndDevice;
    private final Date date;
    private final Command action;

    public Event(String residentAndDevice, Date date, Command action) {
        this.residentAndDevice = residentAndDevice;
        this.date = date;
        this.action = action;
    }

    public String getResidentAndDevice() {
        return residentAndDevice;
    }

    public Date getDate() {
        return date;
    }

    public Command getAction() {
        return action;
    }
}
