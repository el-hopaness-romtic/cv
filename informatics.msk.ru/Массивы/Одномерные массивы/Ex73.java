import java.util.Scanner;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;

public class Ex73 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), currentMax = scanner.nextInt(), counter = 1;

        for (int i = 1; i < n; i++) {
            int el = scanner.nextInt();
            if (el > currentMax) {
                currentMax = el;
                counter++;
            }
        }
        System.out.println(counter);
    }
}
