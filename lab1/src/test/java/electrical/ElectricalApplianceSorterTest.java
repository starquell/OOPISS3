package electrical;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.manipulation.Ordering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class ElectricalApplianceSorterTest {

    private ElectricalAppliance[] appliances;

    @BeforeEach
    void init() {
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
    void sortByPower() {
        var sorted = ElectricalApplianceSorter.sortByPower(Arrays.asList(appliances));
        var expected = new ElectricalAppliance[] {
                new TV(750),
                new Refrigerator(940),
                new TV(1000),
                new Refrigerator(1600),
                new Dishwasher(2000),
                new Dishwasher(2100)
        };
        assertEquals(Arrays.asList(expected), sorted);
    }

}