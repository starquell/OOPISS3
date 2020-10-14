import electrical.*;

public class Main {

    public static void main (String[] args) {
        ElectricalAppliance[] all = {
                new TV(800),
                new TV(700),
                new TV(650),
                new Refrigerator(1300),
                new Refrigerator(1600),
                new Refrigerator(1400),
                new Dishwasher(1600),
                new Dishwasher(1350),
                new Dishwasher(1800),
        };
        var circuit = new Circuit(5);

        var cli = new CLI(all, circuit);
        cli.run();
    }
}