import java.util.Scanner;

public class Ex307 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        int n = scanner.nextInt();

        System.out.println(power(a, n));
    }

    static double power(double a, int n) {
        double res = 1;
        for (int i = 0; i < n; i++) {
            res *= a;
        }
        return res;
    }
}
