package electrical;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CircuitTest {

    @Test
    void powerConsumption() throws BusyRosseteException {
        var circuit = new Circuit(10);
        ElectricalAppliance[] appliance = {
                new TV(1000),
                new Refrigerator(2300),
                new Dishwasher(2600),
                new TV(700)
        };

        for (int i = 0; i < appliance.length; ++i) {
            circuit.rosettes().get(i).connect(appliance[i]);
        }
        assertEquals(Arrays.stream(appliance)
                            .mapToInt(ElectricalAppliance::power)
                            .sum(),
                circuit.powerConsumption());
    }
}