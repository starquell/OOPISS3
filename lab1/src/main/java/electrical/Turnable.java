package electrical;

interface Turnable {

    void turnOn() throws NoElectricityException;
    void turnOff() throws NoElectricityException;

    boolean turned();
}