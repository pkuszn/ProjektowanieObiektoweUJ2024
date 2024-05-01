public class SN7432 {
    public static int OR(int A, int B) {
        if (A == 1 || B == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int SN7432(int A, int B) {
        return OR(A, B);
    }

    public static void main(String[] args) {
        // Przykładowe wejścia
        int A = 0;
        int B = 1;

        // Wywołanie funkcji dla układu 7432
        int Y = SN7432(A, B);

        // Wyświetlenie wyniku
        System.out.println("Y: " + Y);
    }
}
