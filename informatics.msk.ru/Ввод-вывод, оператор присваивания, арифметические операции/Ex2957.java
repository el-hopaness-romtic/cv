import java.util.Scanner;

public class Ex2957 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        System.out.print(1 + (n % m) * (m % n));
    }
}
