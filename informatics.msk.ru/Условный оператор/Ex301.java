import java.util.Scanner;

import static java.lang.Math.sqrt;

public class Ex301 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble(), b = scanner.nextDouble(), c = scanner.nextDouble();

        double D = b * b - 4 * a * c;
        if (D >= 0) {
            double x1 = (-b + sqrt(D)) / (2 * a),
                   x2 = (-b - sqrt(D)) / (2 * a);

            if (x1 == x2) System.out.println(x1);
            else System.out.println(x1 + " " + x2);
        }
    }
}
