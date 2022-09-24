import java.util.Scanner;

import static java.lang.Math.sqrt;

public class Ex3081 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt(), s = 0, sSquared = 0, n = 0;

        while (input != 0) {
            n++;
            s += input;
            sSquared += input * input;
            input = scanner.nextInt();
        }

        System.out.println(sqrt((sSquared - (double) s * s / n) / (n - 1)));
    }
}
