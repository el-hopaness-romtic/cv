import java.util.Scanner;

import static java.lang.Math.*;

public class Ex119 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();

        int counter = 0;
        while (k > 0) {
            boolean isPalindrome = true;
            String s = String.valueOf(k);
            for (int i = 0; i < s.length() / 2; i++) {
                if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                    isPalindrome = false;
                    break;
                }
            }
            k--;
            counter += isPalindrome ? 1 : 0;
        }
        System.out.println(counter);
    }
}
