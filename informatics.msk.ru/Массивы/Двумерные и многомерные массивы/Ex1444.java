import java.util.Scanner;

public class Ex1444 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt(), w = scanner.nextInt();
        byte[][] a = new byte[n][m];
        for (; w > 0; w--) {
            int mine_i = scanner.nextInt() - 1, mine_j = scanner.nextInt() - 1;
            for (int i = mine_i - 1; i <= mine_i + 1; i++) {
                for (int j = mine_j - 1; j <= mine_j + 1; j++) {
                    if (0 <= i && i < n && 0 <= j && j < m && a[i][j] != -1)
                        a[i][j]++;
                }

            }

            a[mine_i][mine_j] = -1;
        }

        for (byte[] row : a) {
            for (byte val : row) {
                System.out.print((val == -1 ? "*" : val) + " ");
            }
            System.out.println();
        }
    }
}
