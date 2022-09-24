import java.util.Scanner;

public class Ex3072 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt(), max = 0, counter = 0;
        while (input != 0) {
            if (input > max) {
                max = input;
                counter = 1;
            } else if (input == max) {
                counter++;
            }

            input = scanner.nextInt();
        }
        System.out.println(counter);
    }
}
