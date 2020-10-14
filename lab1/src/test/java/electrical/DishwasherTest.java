package electrical;

import static org.junit.jupiter.api.Assertions.*;

class DishwasherTest {

    @org.junit.jupiter.api.Test
    void testToString() {
        var dishwasher = new Dishwasher(1800);
        assertEquals("Dishwasher, power: 1800 W", dishwasher.toString());
    }
}