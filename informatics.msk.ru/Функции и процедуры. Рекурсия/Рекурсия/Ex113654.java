import java.util.Scanner;

import static java.lang.Math.max;

public class Ex113654 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String n = scanner.nextLine();

        System.out.println(countDigits(n));
    }

    static int countDigits(String n) {
        if (n.length() == 1) return Character.isDigit(n.charAt(0)) ? 1 : 0;
        return countDigits(n.substring(1)) + (Character.isDigit(n.charAt(0)) ? 1 : 0);
    }
}
