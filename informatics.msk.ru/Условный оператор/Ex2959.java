import java.util.Scanner;

public class Ex2959 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        int sgn = 1;
        if (x < 0)
            sgn = -1;
        else if (x == 0)
            sgn = 0;
        System.out.println(sgn);
    }
}
