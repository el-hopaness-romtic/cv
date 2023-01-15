import java.util.Scanner;

public class Ex2951 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt(), n = scanner.nextInt();
        a *= n;
        b *= n;
        System.out.println(a + b / 100 + " " + b % 100);
    }
}
