import java.util.Scanner;

public class Ex317 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), k = scanner.nextInt();

        int numerator = 1, denominator = 1;
        for (int i = n - k + 1; i <= n; i++) {
            numerator *= i;
        }
        for (int i = 2; i <= k; i++) {
            denominator *= i;
        }
        System.out.println(numerator / denominator);
    }

}
