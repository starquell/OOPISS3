package electrical;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TVTest {

    @Test
    void testToString() {
        var tv = new TV(1000);
        assertEquals("TV, power: 1000 W", tv.toString());
    }
}