import java.util.Scanner;

public class Ex113657 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        System.out.println(s + reverseBrackets(s));
    }

    static String reverseBrackets(String s) {
        if (s.length() == 0) return "";
        return (s.charAt(s.length() - 1) == '(' ? ')' : s.charAt(s.length() - 1)) + reverseBrackets(s.substring(0, s.length() - 1));
    }
}
