import java.util.Scanner;

public class Ex333 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt();

        for (int i = a + a % 2; i <= b; i += 2) {
            System.out.print(i + " ");
        }
    }
}
