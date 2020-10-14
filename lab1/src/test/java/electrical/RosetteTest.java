package electrical;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RosetteTest {

    @Test
    void removeConnectedDevice() throws BusyRosseteException, NoConnectedDeviceException {
        var rosette = new Rosette();

        assertThrows(NoConnectedDeviceException.class, rosette::removeConnectedDevice);
        rosette.connect(new Refrigerator(1000));
        assertTrue(rosette.busy());
        rosette.removeConnectedDevice();
        assertFalse(rosette.busy());
        assertNull(rosette.connectedDevice());
    }

    @Test
    void connect() throws BusyRosseteException {
        var rosette = new Rosette();
        CircuitConnectable appliance = new Dishwasher(1000);

        assertFalse(rosette.busy());

        rosette.connect(appliance);
        assertTrue(rosette.busy());
        assertEquals(appliance, rosette.connectedDevice());

        assertThrows(BusyRosseteException.class, () -> {
            rosette.connect(new TV(900));
        });
    }
}