import java.util.Scanner;

public class Ex1445 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt(), n = scanner.nextInt(),
            x = scanner.nextInt(), y = scanner.nextInt();

        if (x + 1 <= m)
            System.out.println(x + 1 + " " + y);
        if (x - 1 >= 1)
            System.out.println(x - 1 + " " + y);
        if (y + 1 <= n)
            System.out.println(x + " " + (y + 1));
        if (y - 1 >= 1)
            System.out.println(x + " " + (y - 1));
    }
}
