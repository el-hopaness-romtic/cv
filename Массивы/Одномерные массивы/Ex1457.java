import java.util.Scanner;

public class Ex1457 {

    private static void reversal(int[] arr, int begin, int end) {
        begin--;
        for (int i = 0; i < (end - begin) / 2; i++) {
            int temp = arr[begin + i];
            arr[begin + i] = arr[end - 1 - i];
            arr[end - 1 - i] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(),
                a = scanner.nextInt(),
                b = scanner.nextInt(),
                c = scanner.nextInt(),
                d = scanner.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        reversal(arr, a, b);
        reversal(arr, c, d);

        for (int el : arr) {
            System.out.println(el);
        }
    }
}
