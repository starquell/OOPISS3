package electrical;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RefrigeratorTest {

    @Test
    void testToString() {
        var friger = new Refrigerator(2000);
        assertEquals("Refrigerator, power: 2000 W", friger.toString());
    }
}