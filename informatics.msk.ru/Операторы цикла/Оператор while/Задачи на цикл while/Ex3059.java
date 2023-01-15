import java.util.Scanner;

import static java.lang.Math.log;

public class Ex3059 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int i = 0, current = 1;
        while (i <= log(n) / log(2)) {
            System.out.print(current + " ");
            current *= 2;
            i++;
        }
    }
}
