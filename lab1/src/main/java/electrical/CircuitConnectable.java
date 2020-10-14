package electrical;

public interface CircuitConnectable {

    int power();

    void connectToCircuit();
    void disconnectFromCircuit();
    boolean connectedToCircuit();
}