import java.util.Scanner;

public class Ex334 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt(), c = scanner.nextInt(), d = scanner.nextInt();

        for (int i = a; i <= b; i++) {
            if (i % d == c)
                System.out.print(i + " ");
        }
    }
}
