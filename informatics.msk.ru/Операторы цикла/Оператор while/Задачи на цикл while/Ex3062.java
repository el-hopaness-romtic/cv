import java.util.Scanner;

public class Ex3062 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double x = scanner.nextDouble(), y = scanner.nextDouble();

        int days = 1;
        while (x < y) {
            x *= 1.1;
            days++;
        }
        System.out.println(days);
    }
}
