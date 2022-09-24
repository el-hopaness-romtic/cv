import java.util.Scanner;

public class Ex353 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int s = 0, current = 1;
        for (int i = 0; i <= n; i++) {
            s += current;
            current *= 2;
        }
        System.out.println(s);
    }
}
