import java.util.Scanner;

public class Ex1448 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        String b;
        int temp = n / 100;
        n %= 100;
        if (n > 10 & n < 15 || n % 10 > 4 || n % 10 == 0)
            b = "bochek";
        else if (n % 10 == 1)
            b = "bochka";
        else
            b = "bochki";
        System.out.println(n + (100 * temp) + " " + b);
    }
}
