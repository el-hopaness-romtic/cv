import java.util.Scanner;

import static java.lang.Integer.signum;

public class Ex346 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] counters = new int[3];
        for (int i = 0; i < n; i++)
            counters[signum(scanner.nextInt()) + 1]++;

        System.out.print(counters[1] + " " + counters[2] + " " + counters[0]);
    }

}
