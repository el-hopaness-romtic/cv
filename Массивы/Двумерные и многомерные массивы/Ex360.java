import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.MIN_VALUE;

public class Ex360 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        int[] pos = new int[n];
        int max = MIN_VALUE, counter = 0;
        for (int i = 0; i < n; i++) {
            int playerMax = Arrays.stream(arr[i]).max().getAsInt();
            if (playerMax > max) {
                counter = 1;
                pos[0] = i;
                max = playerMax;
            } else if (playerMax == max) {
                pos[counter] = i;
                counter++;
            }
        }
        System.out.println(counter);
        for (int i = 0; i < counter; i++) {
            System.out.println(pos[i]);
        }
    }
}
