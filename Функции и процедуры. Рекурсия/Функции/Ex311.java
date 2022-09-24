import java.util.Scanner;

public class Ex311 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        int n = scanner.nextInt();

        System.out.print(fastPower(a, n));
    }

    static double fastPower(double a, int n) {
        if (a == 0)
            return 0;

        double res = 1;
        while (n > 1) {
            if (n % 2 == 0) {
                a = a * a;
                n = n / 2;
            } else {
                res *= a;
                a = a * a;
                n = (n - 1) / 2;
            }
        }
        if (n == 1)
            res *= a;

        return res;
    }
}
