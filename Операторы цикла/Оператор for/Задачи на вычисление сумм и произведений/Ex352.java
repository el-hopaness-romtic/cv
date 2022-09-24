import java.util.Scanner;

public class Ex352 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int power = 1;
        for (int i = 1; i <= n; i++) {
            power *= 2;
        }
        System.out.println(power);
    }

}
