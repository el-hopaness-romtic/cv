import java.util.Scanner;

public class Ex117 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        while (n > 0) {
            System.out.print(n % 2);
            n /= 2;
        }
    }
}
