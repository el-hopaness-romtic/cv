import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.MIN_VALUE;

public class Ex356 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        int imax = 0, max = MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int score = Arrays.stream(arr[i]).sum();
            if (score > max) {
                imax = i;
                max = score;
            }
        }
        System.out.println(max);
        System.out.println(imax);
    }
}
