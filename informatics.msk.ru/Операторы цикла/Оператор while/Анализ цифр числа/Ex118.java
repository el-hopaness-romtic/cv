import java.util.Scanner;

public class Ex118 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        while (n > 0) {
            System.out.print(n % 10);
            n /= 10;
        }
    }
}
