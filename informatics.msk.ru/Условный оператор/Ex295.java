import java.util.Scanner;

public class Ex295 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt(), c = scanner.nextInt();

        System.out.println(a + b > c && a + c > b && b + c > a ? "YES" : "NO");
    }
}
