import java.util.Scanner;

public class Ex354 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == n - i - 1)
                    arr[i][j] = 1;
                else if (j > n - i - 1)
                    arr[i][j] = 2;
            }
        }

        for (var row : arr) {
            for (var el : row) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }
}
