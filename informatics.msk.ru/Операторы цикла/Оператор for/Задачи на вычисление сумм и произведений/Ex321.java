import java.util.Scanner;

public class Ex321 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        double s = 0;
        for (int i = 0; i <= n; i++) {
            s += Math.pow(-1, i) / (2 * i + 1);
        }
        System.out.println(4 * s);
    }
}
