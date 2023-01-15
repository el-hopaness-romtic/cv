import java.util.Scanner;

public class Ex2947 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        n %= 24 * 60;
        System.out.print(n / 60 + " " + n % 60);
    }
}
