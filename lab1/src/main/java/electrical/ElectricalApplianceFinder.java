package electrical;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ElectricalApplianceFinder {

    static public Optional<ElectricalAppliance> find (List<ElectricalAppliance> appliance,
                                                      Predicate<ElectricalAppliance> pred) {
        return appliance.stream()
                        .filter(pred)
                        .findFirst();
    }
}
