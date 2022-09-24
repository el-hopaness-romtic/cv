import java.util.Scanner;

import static java.lang.Math.max;

public class Ex294 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt(), c = scanner.nextInt();

        System.out.println(max(max(a, b), max(b, c)));
    }
}
