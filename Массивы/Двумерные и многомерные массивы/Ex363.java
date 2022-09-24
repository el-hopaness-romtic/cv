import java.util.Scanner;

public class Ex363 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        int[][] a = new int[n][m];
        for (int i = 0, j = 0, counter = -1; i < n; i++) {
            if (j == 0) {
                for (; j < m; j++)
                    a[i][j] = ++counter;
                j--;
            } else {
                for (; j >= 0; j--)
                    a[i][j] = ++counter;
                j++;
            }
        }

        for (int[] row : a) {
            for (int val : row) {
                System.out.printf("%3d ", val);
            }
            System.out.println();
        }
    }
}
