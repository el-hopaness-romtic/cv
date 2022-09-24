import java.util.Scanner;

import static java.lang.Math.signum;

public class Ex68 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), counter = 0;
        if (n < 3) {
            System.out.println(counter);
            return;
        }

        int prev = scanner.nextInt(), curr = scanner.nextInt(), next;
        for (int i = 2; i < n; i++) {
            next = scanner.nextInt();
            if (prev < curr && curr > next)
                counter++;

            prev = curr;
            curr = next;
        }
        System.out.println(counter);
    }
}
