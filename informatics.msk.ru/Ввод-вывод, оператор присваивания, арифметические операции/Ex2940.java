import java.util.Scanner;

public class Ex2940 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int vt = scanner.nextInt() * scanner.nextInt();
        int res = (109 + vt % 109) % 109;
        System.out.print(res);
    }
}
