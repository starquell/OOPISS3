package com.example.lab3;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class DTWAlgoTest {
    @Test
    public void main() {
        final List<Float> first = Arrays.asList(6.2f, 1f, 1f, 12.5f, 8f);
        final List<Float> second = Arrays.asList(8f, 6f, 2.1f, 3f, 0.3f);

        Float distance = DTWAlgo.distance(first, second);

        assertTrue(distance.equals(267f));
    }
}
