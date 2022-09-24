import java.util.*;

import static java.lang.Integer.MAX_VALUE;

public class Ex3076 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt();

        int right = 2 * b;
        while (a >= right)
            right *= 2;
        int left = right / 2;

        while (true) {
            if (left == b) {
                while (a != b) {
                    a -= 1;
                    System.out.println("-1");
                }
                break;
            }

            if (a == left) {
                while (a != b) {
                    a /= 2;
                    System.out.println(":2");
                }
                break;
            }

            if (a % 2 == 1) {
                a -= 1;
                System.out.println("-1");

            } else {
                a /= 2;
                System.out.println(":2");
                left /= 2;
            }
        }

    }
}
