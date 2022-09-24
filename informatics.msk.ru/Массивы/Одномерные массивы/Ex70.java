import java.util.Scanner;

public class Ex70 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 1; i < n; i += 2) {
            int temp = scanner.nextInt();
            System.out.println(scanner.nextInt());
            System.out.println(temp);
        }
        if (n % 2 == 1)
            System.out.println(scanner.nextInt());
    }
}
