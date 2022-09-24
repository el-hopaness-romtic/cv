import java.util.Scanner;

public class Ex65 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), counter = 0;

        for (int i = 0; i < n; i++) {
            int el = scanner.nextInt();
            if (el > 0) counter++;
        }
        System.out.println(counter);
    }
}
