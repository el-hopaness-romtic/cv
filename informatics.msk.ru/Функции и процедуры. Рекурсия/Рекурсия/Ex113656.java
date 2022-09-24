import java.util.Scanner;

public class Ex113656 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        System.out.println(insertBrackets(s));
    }

    static String insertBrackets(String s) {
        if (s.length() <= 2) return s;
        return s.charAt(0) + "(" + insertBrackets(s.substring(1, s.length() - 1)) + ")" + s.charAt(s.length() - 1);
    }
}
