import java.util.Scanner;

import static java.lang.Math.signum;

public class Ex67 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int curr, prev = scanner.nextInt();
        for (int i = 1; i < n; i++) {
            curr = scanner.nextInt();
            if (signum(curr) == signum(prev)) {
                System.out.println("YES");
                return;
            }
            prev = curr;
        }
        System.out.println("NO");
    }
}
