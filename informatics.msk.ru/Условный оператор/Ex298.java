import java.util.Scanner;

import static java.lang.Math.abs;

public class Ex298 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x1 = scanner.nextInt(), y1 = scanner.nextInt(),
            x2 = scanner.nextInt(), y2 = scanner.nextInt();

        System.out.println(abs(x1 - x2) < 2 && abs(y1 - y2) < 2 ? "YES" : "NO");
    }
}
