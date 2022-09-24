import java.util.Scanner;

public class Ex361 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();

        for (int i = 0; i < n * m; i++) {
            if (i > 0 && i % m == 0)
                System.out.println();
            System.out.print((i / m) * (i % m) + " ");
        }
    }
}
