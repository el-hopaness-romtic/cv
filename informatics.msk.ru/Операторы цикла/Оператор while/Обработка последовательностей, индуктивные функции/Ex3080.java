import java.util.Scanner;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

public class Ex3080 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input1 = scanner.nextInt(), input2 = scanner.nextInt();
        if (input2 == 0) {
            System.out.println(0);
            return;
        }
        int input3 = scanner.nextInt(), locMax1 = 0, locMax2 = 0, minDistance = MAX_VALUE, i = 1;
        while (input3 != 0) {
            if (input1 < input2 && input2 > input3) {
                locMax1 = locMax2;
                locMax2 = i;
                if (locMax1 != 0)
                    minDistance = min(minDistance, locMax2 - locMax1);
            }

            input1 = input2;
            input2 = input3;
            input3 = scanner.nextInt();
            i++;
        }

        System.out.println(locMax1 == 0 ? 0 : minDistance);
    }
}
