import java.util.Arrays;

class Demo {
    private static final int NUM_TICKS = 4; // Liczba taktów zegara

    // Stan wejść układu
    private static boolean[] inputs = new boolean[] { true, false, true, true };

    // Tablica przechowująca stany wyjść układu
    private static boolean[] outputs = new boolean[4];

    // Metoda symulująca działanie układu dla jednego taktu zegara
    private static void simulateOneTick() {
        // Obliczenie stanów wyjść na podstawie stanów wejść
        outputs[0] = !inputs[0] && inputs[1] && inputs[3];
        outputs[1] = !inputs[2];
        outputs[2] = inputs[1] || outputs[1];
        outputs[3] = inputs[3];

        // Aktualizacja stanów wejść na podstawie stanów wyjść
        inputs[2] = outputs[1];
    }

    public static void main(String[] args) {
        // Wyświetlenie stanu wejść na początku symulacji
        System.out.println("Inputs at t=0: " + Arrays.toString(inputs));

        // Symulacja dla kolejnych taktów zegara
        for (int i = 0; i < NUM_TICKS; i++) {
            simulateOneTick();
            System.out.println("Outputs at t=" + (i + 1) + ": " + Arrays.toString(outputs));
        }
    }
}