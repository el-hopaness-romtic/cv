import java.util.Scanner;

public class Ex355 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1 + i; j < n; j++) {
                if (arr[i][j] != arr[j][i]) {
                    System.out.println("no");
                    return;
                }
            }
        }
        System.out.println("yes");
    }
}
