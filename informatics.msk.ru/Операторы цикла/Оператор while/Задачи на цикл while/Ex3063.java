import java.util.Scanner;

public class Ex3063 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt() * 100, p = scanner.nextInt(), y = scanner.nextInt() * 100;

        double pp = 1 + p / 100.0;
        int years = 0;
        while (x < y) {
            x *= pp;
            years++;
        }
        System.out.println(years);
    }
}
