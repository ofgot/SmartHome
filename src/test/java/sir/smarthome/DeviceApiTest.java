package sir.smarthome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sir.smarthome.commands.Command;
import sir.smarthome.commands.DeviceApi;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeviceApiTest {

    private DeviceApi deviceApi;
    private Command action;

    @BeforeEach
    void setUp() {
        deviceApi = new DeviceApi();
    }

    @Test
    void testExecuteActionWithCommand() {
        action = mock(Command.class);

        deviceApi.setAction(action);

        deviceApi.executeAction();

        verify(action).execute();
    }


}
