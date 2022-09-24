import java.util.Scanner;

public class Ex3075 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();

        int a = 0, b = 1, n = 1;
        while (b < A) {
            int t = a + b;
            a = b;
            b = t;
            n++;
        }
        System.out.println(b == A ? n : -1);
    }
}
