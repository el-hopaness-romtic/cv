import java.util.Scanner;

public class Ex113658 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        System.out.println(removeSymmetrical(s));
    }

    static String removeSymmetrical(String s) {
        if (s.length() == 0) return "";
        if (s.charAt(0) == s.charAt(s.length() - 1))
            return removeSymmetrical(s.substring(1, s.length() - 1));
        return s.charAt(0) + removeSymmetrical(s.substring(1, s.length() - 1)) + s.charAt(s.length() - 1);
    }
}
