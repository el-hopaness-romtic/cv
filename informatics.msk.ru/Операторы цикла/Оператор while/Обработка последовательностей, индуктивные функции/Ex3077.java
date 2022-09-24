import java.util.Scanner;

import static java.lang.Math.max;

public class Ex3077 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt(), current = input, counter =0, maxCounter =0;

        if (input  == 0) {
            System.out.println(0);
            return;
        }

        while (input != 0) {
            if (input == current) {
                counter++;
            } else {
                maxCounter = max(maxCounter, counter);
                current = input;
                counter = 1;
            }

            input = scanner.nextInt();
        }
        System.out.println(max(maxCounter, counter));
    }
}
