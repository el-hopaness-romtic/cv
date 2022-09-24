import java.util.Scanner;

public class Ex1430 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int current = 1;
        while (n > 0) {
            for (int i = 0; i < current && n > 0; i++, n--) {
                System.out.print(current + " ");
            }
            current++;
        }
    }
}
