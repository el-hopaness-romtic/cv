import java.util.Scanner;

public class Ex265 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt(), m = scanner.nextInt(), n = scanner.nextInt();

        int time;
        if (n <= k) {
            time = 2;
        } else if (2 * n % k != 0) {
            time = 2 * n / k + 1;
        } else {
            time = 2 * n / k;
        }

        System.out.println(m * time);
    }
}
