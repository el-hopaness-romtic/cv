import java.util.Scanner;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;

public class Ex1456 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int petya = scanner.nextInt();

        int i = 0;
        for (; i < n; i++) {
            if (petya > arr[i])
                break;
        }
        System.out.println(i + 1);
    }
}
