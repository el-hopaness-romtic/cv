import java.util.Scanner;

public class Ex113655 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        System.out.println(insertAsterisks(s));
    }

    static String insertAsterisks(String s) {
        if (s.length() == 1) return s;
        return s.charAt(0) + "*" + insertAsterisks(s.substring(1));
    }
}
