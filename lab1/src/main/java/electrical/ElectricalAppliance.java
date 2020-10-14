package electrical;

public abstract class ElectricalAppliance implements CircuitConnectable, Turnable {

    private boolean _connected;
    private final int _power;
    private boolean _turnedOn;

    ElectricalAppliance (int power) {
        _connected = false;
        _power = power;
    }

    @Override
    public void turnOn() throws NoElectricityException {
        if (!connectedToCircuit()) {
            throw new NoElectricityException("Cant turn on TV without electricity");
        }
        _turnedOn = true;
    }

    @Override
    public void turnOff() throws NoElectricityException {
        if (!connectedToCircuit()) {
            throw new NoElectricityException("Cant turn off TV without electricity");
        }
        _turnedOn = false;
    }

    @Override
    public boolean turned() {
        return _turnedOn;
    }

    @Override
    public void connectToCircuit() {
        _connected = true;
    }

    @Override
    public void disconnectFromCircuit() {
        _connected = false;
    }

    public boolean connectedToCircuit() {
        return _connected;
    }

    @Override
    public int power() {
        return _power;
    }
}

