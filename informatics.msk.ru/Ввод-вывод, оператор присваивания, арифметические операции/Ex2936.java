import java.util.Scanner;

import static java.lang.Math.sqrt;

public class Ex2936 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt();
        System.out.println(sqrt(a * a + b * b));
    }
}
