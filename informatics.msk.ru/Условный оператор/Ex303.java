import java.util.Scanner;

import static java.lang.Math.abs;

public class Ex303 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if (n > 10 & n < 15 || n % 10 > 4 || n % 10 == 0)
            System.out.println(n + " korov");
        else if (n % 10 == 1)
            System.out.println(n + " korova");
        else
            System.out.println(n + " korovy");
    }
}
