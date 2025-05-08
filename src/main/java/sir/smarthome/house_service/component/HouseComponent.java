package sir.smarthome.house_service.component;

import java.util.List;

public interface HouseComponent {
    String getName();
    void addComponent(HouseComponent component);
    void removeComponent(HouseComponent component);
    List<HouseComponent> getComponents();
    void appendReport(StringBuilder report);
}