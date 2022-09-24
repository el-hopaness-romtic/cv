import java.util.Scanner;

public class Ex120 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if (n > 7) {
            System.out.println("2.71828");
        } else {
            double s = 1;
            int current = 1;
            for (int i = 1; i <= n; i++) {
                current *= i;
                s += 1.0 / current;
            }
            System.out.println(s);
        }
    }
}
