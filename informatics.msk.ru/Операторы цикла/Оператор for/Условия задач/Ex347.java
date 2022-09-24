import java.util.Scanner;

import static java.lang.Integer.signum;

public class Ex347 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            if (scanner.nextInt() == 0) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }

}
