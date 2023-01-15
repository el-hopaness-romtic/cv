import java.util.Scanner;

import static java.lang.Math.abs;

public class Ex1451 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt(), c = scanner.nextInt();

        boolean aIsOdd = abs(a) % 2 == 1,
                bIsOdd = abs(b) % 2 == 1,
                cIsOdd = abs(c) % 2 == 1;

        System.out.println((aIsOdd || bIsOdd || cIsOdd) && (!aIsOdd || !bIsOdd || !cIsOdd) ? "YES" : "NO");
    }
}
