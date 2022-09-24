import java.util.Scanner;

public class Ex261 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt(),
            c = scanner.nextInt(), d = scanner.nextInt();

        String res = "NO";
        if (a == 0 && b == 0)
            res = "INF";
        else if (a != 0 && b % a == 0 && (c == 0 || b / a != d / c))
            res = String.valueOf(-b / a);

        System.out.println(res);
    }
}
