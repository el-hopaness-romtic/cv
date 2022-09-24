import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.MIN_VALUE;

public class Ex358 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        int imax = 0, max = MIN_VALUE, sum = MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int playerMax = Arrays.stream(arr[i]).max().getAsInt();
            if (playerMax > max) {
                imax = i;
                max = playerMax;
                sum = Arrays.stream(arr[i]).sum();
            } else if (playerMax == max) {
                int playerSum = Arrays.stream(arr[i]).sum();
                if (playerSum > sum) {
                    imax = i;
                    max = playerMax;
                    sum = playerSum;
                }
            }
        }
        System.out.println(imax);
    }
}
