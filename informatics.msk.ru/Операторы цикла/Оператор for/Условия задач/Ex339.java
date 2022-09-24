import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Ex339 {
    private static final int UPPER_LIMIT = 30000;
    private static final List<Integer> sieveOfEratosthenes = sieveOfEratosthenes();

    private static List<Integer> sieveOfEratosthenes() {
        boolean[] prime = new boolean[UPPER_LIMIT + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= UPPER_LIMIT; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= UPPER_LIMIT; i += p) {
                    prime[i] = false;
                }
            }
        }
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= UPPER_LIMIT; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        for (int i : sieveOfEratosthenes) {
            if (x % i == 0) {
                System.out.println(i);
                break;
            }
        }
    }
}
