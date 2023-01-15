import java.util.Scanner;

public class Ex2949 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt();
        int t = a;
        a = b;
        b = t;
        System.out.println(a + " " + b);
    }
}
