import java.util.Scanner;

public class Ex3074 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if (n < 2) {
            System.out.println(n);
            return;
        }

        int a = 0, b = 1;
        while (n > 1) {
            int t = a + b;
            a = b;
            b = t;
            n--;
        }
        System.out.println(b);
    }
}
