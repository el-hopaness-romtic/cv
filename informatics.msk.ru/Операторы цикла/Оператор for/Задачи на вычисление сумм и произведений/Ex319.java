import java.util.Scanner;

public class Ex319 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        int n = scanner.nextInt();

        double s = 0, temp = 1;
        for (int i = 0; i <= n; i++) {
            s += temp;
            temp *= a;
        }
        System.out.println(s);
    }
}
