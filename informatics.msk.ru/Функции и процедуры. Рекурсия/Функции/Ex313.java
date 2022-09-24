import java.util.Scanner;

public class Ex313 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), k = scanner.nextInt();

        System.out.println(binomial(k, n));
    }

    static int binomial(int k, int n) {
        if (k == 0 || k == n) return 1;

        return binomial(k - 1, n - 1) + binomial(k, n - 1);
    }
}
