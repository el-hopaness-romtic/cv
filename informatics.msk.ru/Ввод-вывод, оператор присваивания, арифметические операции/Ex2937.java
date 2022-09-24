import java.util.Scanner;

import static java.lang.Math.sqrt;

public class Ex2937 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.printf("The next number for the number %d is %d.\n", num, num + 1);
        System.out.printf("The previous number for the number %d is %d.", num, num - 1);
    }

}
