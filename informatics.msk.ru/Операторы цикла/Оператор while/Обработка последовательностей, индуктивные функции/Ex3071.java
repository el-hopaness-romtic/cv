import java.util.Scanner;

public class Ex3071 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt(), max1 = 0, max2 = 0;
        while (input != 0) {
            if (input >= max1) {
                max2 = max1;
                max1 = input;
            } else if (input > max2) {
                max2 = input;
            }

            input = scanner.nextInt();
        }
        System.out.println(max2);
    }
}
