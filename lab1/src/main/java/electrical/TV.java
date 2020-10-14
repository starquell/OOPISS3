package electrical;

public class TV extends ElectricalAppliance {

    public TV(int power) {
        super(power);
    }

    @Override
    public void turnOn() throws NoElectricityException {
        super.turnOn();
        System.out.println("TV turned on!");
    }

    @Override
    public void turnOff() throws NoElectricityException {
        super.turnOff();
        System.out.println("TV turned off!");
    }

    @Override
    public boolean equals(Object other) {
         if (other == this) {
            return true;
        }
        if (!(other instanceof TV)) {
            return false;
        }
        var casted = (TV) other;
        return casted.power() == this.power();
    }

    @Override
    public String toString() {
        return "TV, power: " + power() + " W";
    }
}


