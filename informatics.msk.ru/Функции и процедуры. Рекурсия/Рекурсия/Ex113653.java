import java.util.Scanner;

import static java.lang.Math.max;

public class Ex113653 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String n = scanner.nextLine();

        System.out.println(maxDigit(n));
    }

    static char maxDigit(String n) {
        if (n.length() == 1) return n.charAt(0);
        return (char) max(n.charAt(0), maxDigit(n.substring(1)));
    }
}
