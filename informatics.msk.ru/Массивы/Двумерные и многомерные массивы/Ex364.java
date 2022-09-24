import java.util.Scanner;

public class Ex364 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        int[][] a = new int[n][m];
        for (int i = 0, j = 0, counter = -1, d = 0; counter < n * m - 1; j = ++d, i = 0) {
            for (; j >= 0; i++, j--)
                if (j < m && i < n)
                    a[i][j] = ++counter;
        }

        for (int[] row : a) {
            for (int val : row) {
                System.out.printf("%3d ", val);
            }
            System.out.println();
        }
    }
}
