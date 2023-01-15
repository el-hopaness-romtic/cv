import java.util.Scanner;

import static java.lang.Integer.signum;

public class Ex266 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x1 = scanner.nextInt(), y1 = scanner.nextInt(),
            x2 = scanner.nextInt(), y2 = scanner.nextInt();

        System.out.println(signum(x1) == signum(x2) && signum(y1) == signum(y2) ? "YES" : "NO");
    }
}
