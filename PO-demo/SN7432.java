public class SN7432 {
    // Metoda realizująca funkcję logiczną bramki OR
    public static int OR(int A, int B) {
        if (A == 1 || B == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    // Implementacja bramki 7432
    public static int[] SN7432(int[] inputs) {
        int[] outputs = new int[4];
        for (int i = 0; i < 4; i++) {
            int A = inputs[2 * i];
            int B = inputs[2 * i + 1];
            outputs[i] = OR(A, B);
        }
        return outputs;
    }

    public static void main(String[] args) {
        // Przykładowe wejścia dla wszystkich bramek
        int[] inputs = {0, 1, 1, 0, 0, 0, 1, 1}; // 8 wejść dla 4 bramek

        // Wywołanie funkcji dla układu 7432
        int[] outputs = SN7432(inputs);

        // Wyświetlenie wyników
        for (int i = 0; i < 4; i++) {
            System.out.println("Wyjście " + (i + 1) + ": " + outputs[i]);
        }
    }
}
