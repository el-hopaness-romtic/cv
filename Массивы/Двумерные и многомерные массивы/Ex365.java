import java.util.Scanner;

public class Ex365 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] a = new int[2 * n + 1][2 * n + 1];
        for (int r = 1, counter = 0, i = n, j = n - 1; j >= 0; r++) {
            for (; i > n - r; i--)
                a[j][i] = ++counter;

            for (; j < n + r; j++)
                a[j][i] = ++counter;

            for (; i < n + r; i++)
                a[j][i] = ++counter;

            for (; j >= n - r; j--)
                a[j][i] = ++counter;
        }

        for (int[] row : a) {
            for (int val : row) {
                System.out.printf("%3d ", val);
            }
            System.out.println();
        }
    }
}
