package electrical;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ElectricalApplianceFinderTest {

    private ElectricalAppliance[] appliances;

    @BeforeEach
    public void init() {
        appliances = new ElectricalAppliance[] {
             new TV(1000),
             new Dishwasher(2000),
             new Refrigerator(1600),
             new TV(750),
             new Dishwasher(2100),
             new Refrigerator(940)
        };
    }

    @Test
    void findLittlePower() {
        var found = ElectricalApplianceFinder.find(
                Arrays.asList(appliances),
                appliance -> appliance.power() < 1000
        );

        assertTrue(found.isPresent());
        assertTrue(found.get().equals(appliances[3]) || found.get().equals(appliances[5]));
    }

    @Test
    void findDishwasher() {
        var found = ElectricalApplianceFinder.find(
                Arrays.asList(appliances),
                appliance -> appliance instanceof Dishwasher
        );
        assertTrue(found.isPresent());
        assertTrue(found.get().equals(appliances[1]) || found.get().equals(appliances[4]));
    }
}
