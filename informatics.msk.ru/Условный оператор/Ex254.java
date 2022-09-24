import java.util.Scanner;

import static java.lang.Math.max;

public class Ex254 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x1 = scanner.nextInt(), y1 = scanner.nextInt(),
            x2 = scanner.nextInt(), y2 = scanner.nextInt();

        System.out.println(x1 == x2 || y1 == y2 ? "YES" : "NO");
    }
}
