import java.util.Scanner;

public class Ex306 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(),
                b = scanner.nextInt(),
                c = scanner.nextInt(),
                d = scanner.nextInt();

        System.out.println(min(a, b, c, d));
    }

    static int min(int a, int b, int c, int d) {
        int min1 = a < b ? a : b, min2 = c < d ? c : d;
        return min1 < min2 ? min1 : min2;
    }
}
