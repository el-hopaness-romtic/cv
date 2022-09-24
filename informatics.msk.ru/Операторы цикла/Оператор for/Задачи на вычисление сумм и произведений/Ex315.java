import java.util.Scanner;

public class Ex315 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int s = 0;
        for (int i = 1; i <= n; i++) {
            s += i * i;
        }
        System.out.println(s);
    }

}
