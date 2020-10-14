package electrical;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ElectricalApplianceTest {

    @Test
    void turnOn() throws NoElectricityException {
        ElectricalAppliance appliance = new Dishwasher(2400);
        assertThrows(NoElectricityException.class, appliance::turnOn);

        appliance.connectToCircuit();
        appliance.turnOn();

        assertTrue(appliance.turned());
    }

    @Test
    void turnOff() throws NoElectricityException {
        ElectricalAppliance appliance = new TV(1050);
        assertThrows(NoElectricityException.class, appliance::turnOff);

        appliance.connectToCircuit();
        appliance.turnOff();

        assertFalse(appliance.turned());
    }

    @Test
    void connectToCircuit() {
        ElectricalAppliance appliance = new TV(1000);
        appliance.connectToCircuit();
        assertTrue(appliance.connectedToCircuit());
    }

    @Test
    void disconnectFromCircuit() {
        ElectricalAppliance appliance = new TV(1000);
        appliance.connectToCircuit();
        appliance.disconnectFromCircuit();
        assertFalse(appliance.connectedToCircuit());
    }

    @Test
    void power() {
        int[] powers = {100, 200, 400, 1400, 1340};
        var appliance = (ElectricalAppliance[]) Arrays
                                                 .stream(powers)
                                                 .mapToObj(Refrigerator::new)
                                                 .toArray(ElectricalAppliance[]::new);

        assertArrayEquals(powers, Arrays.stream(appliance)
                                   .mapToInt(ElectricalAppliance::power)
                                   .toArray());
    }
}