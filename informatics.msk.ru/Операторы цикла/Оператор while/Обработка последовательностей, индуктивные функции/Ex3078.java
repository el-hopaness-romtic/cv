import java.util.Scanner;

import static java.lang.Math.max;

public class Ex3078 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int input1 = scanner.nextInt(), input2 = scanner.nextInt(), counter = 0, maxCounter = 1;

        if (input1 == 0) {
            System.out.println(0);
            return;
        }
        if (input2 == 0) {
            System.out.println(1);
            return;
        }

        Boolean increasing = null;
        do {
            if (increasing == null) {
                if (input1 != input2) {
                    increasing = input1 < input2;
                    counter = 2;
                }
            } else if (increasing && input1 < input2 || !increasing && input1 > input2) {
                counter++;
            } else {
                maxCounter = max(maxCounter, counter);
                if (input1 != input2) {
                    increasing = input1 < input2;
                    counter = 2;
                } else {
                    increasing = null;
                }
            }

            input1 = input2;
            input2 = scanner.nextInt();
        } while (input2 != 0);
        System.out.println(max(maxCounter, counter));
    }
}
