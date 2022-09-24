import java.util.Scanner;

import static java.lang.Math.abs;

public class Ex260 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt();

        String res = "NO";
        if (a == 0 && b == 0)
            res = "INF";
        else if (a != 0 && b % a == 0)
            res = String.valueOf(-b / a);

        System.out.println(res);
    }
}
