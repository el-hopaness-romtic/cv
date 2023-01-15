import java.util.Scanner;

public class Ex320 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        double s = 0;
        for (int i = 1; i <= n; i++) {
            s += 1.0 / (i * i);
        }
        System.out.println(s);
    }
}
