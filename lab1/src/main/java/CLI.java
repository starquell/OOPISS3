import electrical.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CLI {

    private ElectricalAppliance[] appliance;
    private Circuit circuit;

    public CLI (ElectricalAppliance[] appliance, Circuit circuit) {
        this.appliance = appliance;
        this.circuit = circuit;
    }

    private void printAppliance (List<ElectricalAppliance> appliance) {
        System.out.println();
        for (var app : appliance) {
            System.out.println(app.toString());
        }
        System.out.println();
    }

    private void printAppliance (ElectricalAppliance appliance) {
        System.out.println();
        System.out.println(appliance.toString());
        System.out.println();
    }

    private void printHelp () {
         System.out.println(
                "list - output all electrical appliance\n" +
                "sort - sort appliance\n" +
                "find more n - output one appliance with power more than n\n" +
                "circuit - output all electical appliance in circuit\n" +
                "power - output power consumption in circuit\n" +
                "add n m - add nth appliance to m rosette in circuit\n" +
                "help - output this message\n" +
                "exit\n\n"
        );
    }

    public void run()  {

        printHelp();
        var sc = new Scanner(System.in);

        while (true) {
            String query = sc.nextLine();
            var splitted = query.split(" ");
            switch (splitted[0]) {
                case "list" -> printAppliance(Arrays.asList(appliance));
                case "sort" -> ElectricalApplianceSorter.sortByPower(Arrays.asList(appliance));
                case "find" -> {
                    if (splitted.length != 3) {
                        continue;
                    }
                    int power;
                    try {
                        power = Integer.parseInt(splitted[2]);
                    }
                    catch (Throwable er) {
                        continue;
                    }
                    var found =
                            ElectricalApplianceFinder.find(Arrays.asList(appliance), app -> app.power() > power);
                    found.ifPresent(this::printAppliance);
                }
                case "circuit" -> System.out.println(
                        circuit.rosettes().stream()
                                          .map(rosette -> rosette.busy()
                                                  ? "Rosette with connected device: " + rosette.connectedDevice().toString()
                                                  : "Free rosette!")
                                          .reduce((sum, rosetteInfo) -> sum + "\n" + rosetteInfo)
                                          .orElse(""));
                case "power" -> System.out.println(circuit.powerConsumption());
                case "add" -> {
                    if (splitted.length != 3) {
                        continue;
                    }
                    int n;
                    int rosette;
                    try {
                        n = Integer.parseInt(splitted[1]);
                        rosette = Integer.parseInt(splitted[2]);
                    }
                    catch (Throwable er) {
                        continue;
                    }
                    if (circuit.rosettes().get(rosette).busy()) {
                        System.out.println("Rosette busy!");
                    }
                    else {
                        try {
                            circuit.rosettes().get(rosette - 1).connect(appliance[n - 1]);
                        }
                        catch (Throwable err) {
                            continue;
                        }
                    }
                }
                case "help" -> printHelp();
                case "exit" -> {
                    return;
                }
            }
        }
    }
}
