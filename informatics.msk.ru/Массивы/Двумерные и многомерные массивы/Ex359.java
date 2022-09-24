import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.MIN_VALUE;

public class Ex359 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        int max = MIN_VALUE, counter = 0;
        for (int i = 0; i < n; i++) {
            int playerMax = Arrays.stream(arr[i]).max().getAsInt();

            if (playerMax > max) {
                max = playerMax;
                counter = 1;
            } else if (playerMax == max) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}
