import java.util.Scanner;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;

public class Ex72 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), ans = MIN_VALUE;

        for (int i = 0; i < n; i++) {
            ans = max(ans, scanner.nextInt());
        }
        System.out.println(ans);
    }
}
