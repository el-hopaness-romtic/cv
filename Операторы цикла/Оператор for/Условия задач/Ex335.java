import java.util.Scanner;

import static java.lang.Math.*;

public class Ex335 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt();

        for (int i = (int) sqrt(a) - 1; i <= (int) sqrt(b) + 1; i++) {
            int sqr = i * i;
            if (sqr >= a && sqr <= b)
                System.out.print(sqr + " ");
        }
    }
}
