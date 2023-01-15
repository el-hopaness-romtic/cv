import java.util.Scanner;

import static java.lang.Math.log;

public class Ex3061 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int k = 0, current = 1;
        while (current < n) {
            current *= 2;
            k++;
        }
        System.out.println(k);
    }
}
