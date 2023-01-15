import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Ex341 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        int combinations = 1, smallestFactor = 2, p = 2;
        if (x % p == 0) {
            int counter = 1;
            do {
                x /= p;
                counter++;
            } while (x % p == 0);
            combinations *= counter;
        }
        for (p = 3; p < x / smallestFactor + 1; smallestFactor = p, p += 2) {
            if (x % p == 0) {
                int counter = 1;
                do {
                    x /= p;
                    counter++;
                } while (x % p == 0);
                combinations *= counter;
            }
        }

        if (x != 1) combinations *= 2;
        System.out.print(combinations);
    }

}
