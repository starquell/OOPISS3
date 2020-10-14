package electrical;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;


public class ElectricalApplianceSorter {

    static public List<ElectricalAppliance> sortByPower (List<ElectricalAppliance> appliance) {
        return appliance.stream()
                        .sorted(Comparator.comparing(ElectricalAppliance::power))
                        .collect(Collectors.toList());
    }
}
