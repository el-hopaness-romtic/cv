import java.util.Scanner;

import static java.lang.Math.max;

public class Ex3068 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt(), max = input;
        while (input != 0) {
            input = scanner.nextInt();
            max = max(input, max);
        }
        System.out.println(max);
    }
}
