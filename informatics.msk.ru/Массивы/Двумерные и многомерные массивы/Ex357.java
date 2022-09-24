import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.MIN_VALUE;

public class Ex357 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        int imax = 0, jmax = 0, max = MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (max < arr[i][j]) {
                    max = arr[i][j];
                    imax = i;
                    jmax = j;
                }
            }
        }
        System.out.println(max);
        System.out.println(imax + " " + jmax);

    }
}
