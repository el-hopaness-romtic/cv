import java.util.Scanner;

public class Ex296 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt(), c = scanner.nextInt();

        int res = 0;
        boolean ab = a == b, bc = b == c, ac = a == c;
        if (ab && bc) {
            res = 3;
        } else if (ab || bc || ac) {
            res = 2;
        }

        System.out.println(res);
    }
}
