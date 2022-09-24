import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ex305 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] tickets = new int[5],
              counts = new int[]{60, 20, 10, 5},
              optimalValues = new int[]{35, 17, 8, 4};
        for (int i = 0; i < optimalValues.length && n > 0; i++) {
            tickets[i] = n / counts[i];
            n %= counts[i];
            if (n > optimalValues[i]) {
                tickets[i]++;
                n = 0;
            }
        }
        tickets[4] = n;

        System.out.println(
                IntStream.rangeClosed(1, tickets.length)
                        .mapToObj(i -> tickets[tickets.length - i])
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        );
    }
}
