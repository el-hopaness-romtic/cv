import java.util.Scanner;

public class Ex113652 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        System.out.println(fromOne(n)?"YES":"NO");
    }

    static boolean fromOne(int n) {
        if (n == 1) return true;
        if (n < 1) return false;
        return fromOne(n - 5) || fromOne(n - 3);
    }
}
