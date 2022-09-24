import java.util.Scanner;

public class Ex1464 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        byte[][] a = new byte[n][n];
        for (int r = 0, i = 0, j = 0; r < n / 2 + 1; r += 2) {
            for (; j < n - r; j++)
                a[i][j] = 1;
            j--;

            for (; i < n - r; i++)
                a[i][j] = 1;
            i--;

            for (; j > r - 1; j--)
                a[i][j] = 1;
            j++;

            for (; i > r + 1; i--)
                a[i][j] = 1;
            i++;
        }

        for (byte[] row : a) {
            for (byte val : row) {
                System.out.print(val);
            }
            System.out.println();
        }
    }
}
