import java.util.Scanner;

public class Ex293 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt();

        int res = 0;
        if (a > b)
            res = 1;
        else if (b > a)
            res = 2;

        System.out.println(res);
    }
}
