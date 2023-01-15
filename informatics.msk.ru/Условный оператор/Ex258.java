import java.util.Scanner;

import static java.lang.Math.abs;

public class Ex258 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt(), k = scanner.nextInt();

        System.out.println(k <= m * n && (k % n == 0 || k % m == 0) ? "YES" : "NO");
    }
}
