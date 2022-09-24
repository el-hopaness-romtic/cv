import java.util.Scanner;

public class Ex2948 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        n %= 24 * 60 * 60;
        System.out.printf("%d:%02d:%02d", n / (60 * 60), n % (60 * 60) / 60, n % 60);
    }
}
