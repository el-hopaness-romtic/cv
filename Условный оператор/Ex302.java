import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class Ex302 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt(), c = scanner.nextInt();


        if (a + b > c && a + c > b && b + c > a) {
            int[] arr = new int[]{a, b, c};
            Arrays.sort(arr);
            a = arr[0];
            b = arr[1];
            c = arr[2];

            if (a * a + b * b < c * c)
                System.out.println("obtuse");
            else if (a * a + b * b == c * c)
                System.out.println("right");
            else
                System.out.println("acute");
        } else {
            System.out.println("impossible");
        }
    }
}
