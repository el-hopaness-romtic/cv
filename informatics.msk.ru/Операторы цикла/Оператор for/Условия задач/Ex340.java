import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Ex340 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        if (x == 1)
            System.out.print("1");
        else {
            for (int i = 1; 2 * i <= x; i++) {
                if (x % i == 0) {
                    System.out.print(i + " ");
                }
            }
            System.out.print(x);
        }
    }
}
