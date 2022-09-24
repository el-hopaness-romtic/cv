import java.util.Scanner;

public class Ex71 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int temp = arr[n - 1];
        if (n > 1)
            System.arraycopy(arr, 0, arr, 1, n - 1);
        arr[0] = temp;

        for (int el : arr) {
            System.out.println(el);
        }
    }
}
