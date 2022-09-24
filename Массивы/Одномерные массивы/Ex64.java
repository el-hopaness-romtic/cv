import java.util.Scanner;

public class Ex64 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            int el = scanner.nextInt();
            if (el % 2 == 0) System.out.println(el);
        }
    }
}
