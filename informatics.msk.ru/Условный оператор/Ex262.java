import java.util.Scanner;

public class Ex262 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt(),
            c = scanner.nextInt(), d = scanner.nextInt();

        int change = c * 100 + d - (a * 100 + b);
        System.out.println(change / 100 + " " + change % 100);
    }
}
