import java.util.Scanner;

public class Ex2954 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), k = scanner.nextInt();
        System.out.println((n - k % n) % n);
    }
}
