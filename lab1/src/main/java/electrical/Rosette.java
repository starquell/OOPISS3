package electrical;

class RosetteException extends Exception {
    RosetteException (String s) {
        super(s);
    }
}

class NoConnectedDeviceException extends RosetteException {
    NoConnectedDeviceException () {
        super("No device connect to this rosette");
    }
    NoConnectedDeviceException (String s) {
        super(s);
    }
}

class BusyRosseteException extends RosetteException {

    BusyRosseteException () {
        super("Rosette is used already by other device");
    }
    BusyRosseteException (String s) {
        super(s);
    }
}

public class Rosette {

    private CircuitConnectable current = null;

    public CircuitConnectable connectedDevice() {
        return current;
    }

    public boolean busy() {
        return current != null;
    }

    public void removeConnectedDevice() throws NoConnectedDeviceException {
        if (current == null) {
            throw new NoConnectedDeviceException("No device to remove from rossete.");
        }
        current = null;
    }

    public void connect(CircuitConnectable device) throws BusyRosseteException {
        if (current != null) {
            throw new BusyRosseteException();
        }
        current = device;
    }
}
