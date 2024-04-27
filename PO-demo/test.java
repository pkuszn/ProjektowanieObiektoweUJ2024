import java.util.ArrayList;
import java.util.List;

// Interfejs dla obserwatora układu scalonego
interface CircuitObserver {
    void update();
}

// Klasa reprezentująca układ scalony
class LogicCircuit {
    private boolean state;
    private List<CircuitObserver> observers;

    public LogicCircuit() {
        this.state = false;
        this.observers = new ArrayList<>();
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
        notifyObservers();
    }

    public void addObserver(CircuitObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(CircuitObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (CircuitObserver observer : observers) {
            observer.update();
        }
    }
}

// Implementacja obserwatora układu scalonego
class CircuitMonitor implements CircuitObserver {
    private LogicCircuit circuit;

    public CircuitMonitor(LogicCircuit circuit) {
        this.circuit = circuit;
        this.circuit.addObserver(this);
    }

    @Override
    public void update() {
        boolean newState = circuit.getState();
        System.out.println("State changed: " + newState);
    }
}

public class test {
    public static void main(String[] args) {
        // Tworzymy dwa układy scalone
        LogicCircuit circuit1 = new LogicCircuit();
        LogicCircuit circuit2 = new LogicCircuit();

        // Tworzymy obserwatorów dla obu układów
        CircuitMonitor monitor1 = new CircuitMonitor(circuit1);
        CircuitMonitor monitor2 = new CircuitMonitor(circuit2);

        // Łączymy oba układy
        circuit1.addObserver(new CircuitObserver() {
            @Override
            public void update() {
                // Aktualizujemy stan drugiego układu po zmianie stanu pierwszego układu
                circuit2.setState(circuit1.getState());
            }
        });

        // Zmieniamy stan pierwszego układu
        circuit1.setState(true);

        // Zmieniamy stan drugiego układu
        circuit2.setState(false);
    }
}
