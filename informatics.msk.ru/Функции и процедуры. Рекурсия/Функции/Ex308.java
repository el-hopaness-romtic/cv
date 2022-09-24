import java.util.Scanner;

public class Ex308 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean a = scanner.nextInt() == 1,
                b = scanner.nextInt() == 1;

        System.out.println(xor(a, b) ? 1 : 0);
    }

    static boolean xor(boolean a, boolean b) {
        return a ^ b;
    }
}
