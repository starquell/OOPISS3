package electrical;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Circuit {

    private ArrayList<Rosette> _rossets;

    public Circuit(int rossets) {
        _rossets = Stream.generate(Rosette::new)
                         .limit(rossets)
                         .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Rosette> rosettes() {
        return _rossets;
    }

    public int powerConsumption() {
        return _rossets.stream()
                       .filter(Rosette::busy)
                       .mapToInt(rosette -> rosette.connectedDevice().power())
                       .sum();
    }
}


