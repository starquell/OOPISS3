package electrical;

public class Dishwasher extends ElectricalAppliance {

    public Dishwasher(int power) {
        super(power);
    }

    @Override
    public void turnOn() throws NoElectricityException {
        super.turnOn();
        System.out.println("Dishwasher turned on!");
    }

    @Override
    public void turnOff() throws NoElectricityException {
        super.turnOff();
        System.out.println("Dishwasher turned off!");
    }

    @Override
    public String toString() {
        return "Dishwasher, power: " + power() + " W";
    }

    @Override
    public boolean equals(Object other) {
         if (other == this) {
            return true;
        }
        if (!(other instanceof Dishwasher)) {
            return false;
        }
        var casted = (Dishwasher) other;
        return casted.power() == this.power();
    }
}
