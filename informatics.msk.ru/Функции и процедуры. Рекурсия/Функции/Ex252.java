import java.util.Scanner;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;

public class Ex252 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        int n = scanner.nextInt();

        System.out.println(power(a, n));
    }

    static double power(double a, int n) {
        if (a == 0)
            return 0;

        if (n < 0) {
            n = -n;
            a = 1 / a;
        }

        if (n == 0)
            return 1;
        if (n == 1)
            return a;

        if (n % 2 == 0)
            return power(a * a, n / 2);
        else
            return a * power(a * a, (n - 1) / 2);
    }
}
