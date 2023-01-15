import java.util.Scanner;

public class Ex309 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean x = scanner.nextInt() == 1,
                y = scanner.nextInt() == 1,
                z = scanner.nextInt() == 1;

        System.out.println(Election(x, y, z) ? 1 : 0);
    }

    static boolean Election(boolean x, boolean y, boolean z) {
        if (x == y || x == z) return x;
        return y;
    }
}
