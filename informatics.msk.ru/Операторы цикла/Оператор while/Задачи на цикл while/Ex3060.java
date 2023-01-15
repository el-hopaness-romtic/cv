import java.util.Scanner;

import static java.lang.Math.log;

public class Ex3060 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        while (n % 2 == 0) {
            n /= 2;
        }
        System.out.println(n == 1 ? "YES" : "NO");
    }
}
