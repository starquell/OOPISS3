package electrical;

public class Refrigerator extends ElectricalAppliance {

    public Refrigerator(int power) {
        super(power);
    }

    @Override
    public void turnOn() throws NoElectricityException {
        super.turnOn();
        System.out.println("Friger on!");
    }

    @Override
    public void turnOff() throws NoElectricityException {
        super.turnOff();
        System.out.println("Friger off!");
    }

     @Override
    public String toString() {
        return "Refrigerator, power: " + power() + " W";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Refrigerator)) {
            return false;
        }
        var casted = (Refrigerator) other;
        return casted.power() == this.power();
    }
}
