import java.util.Scanner;

public class Ex312 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        System.out.println(phi(n));
    }

    static int phi(int n) {
        if (n < 2) return 1;

        int a = 1, b = 1;
        while (n > 1) {
            int t = a + b;
            a = b;
            b = t;
            n--;
        }
        return b;
    }
}
