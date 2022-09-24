import java.util.Scanner;

public class Ex115 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int s = 0;
        while (n > 0) {
            s += n % 10 == 0 ? 1 : 0;
            n /= 10;
        }
        System.out.println(s);
    }
}
