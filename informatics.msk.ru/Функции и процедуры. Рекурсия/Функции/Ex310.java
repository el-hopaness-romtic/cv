import java.util.Scanner;

import static java.lang.Math.*;

public class Ex310 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        System.out.println(isPrime(n) ? "prime" : "composite");
    }

    static boolean isPrime(int n) {
        boolean[] factors = new boolean[(int) floor(sqrt(n)) + 1];
        for (int i = 2; i < factors.length; i++) {
            if (!factors[i]) {
                if (n % i == 0)
                    return false;

                for (int j = i; j < factors.length; j += i) {
                    factors[j] = false;
                }
            }
        }
        return true;
    }
}
