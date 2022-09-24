import java.util.Scanner;

public class Ex116 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int min = 10, max = -1;
        while (n > 0) {
            int d = n % 10;
            if (d < min)
                min = d;
            if (d > max)
                max = d;
            n /= 10;
        }
        System.out.println(min + " " + max);
    }
}
