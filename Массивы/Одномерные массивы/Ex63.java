import java.util.Scanner;

import static java.lang.Math.min;

public class Ex63 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            int el = scanner.nextInt();
            if (i % 2 == 0) System.out.println(el);
        }
    }
}
