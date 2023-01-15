import java.util.Scanner;

public class Ex3067 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt(), counter = 0;
        while (input != 0) {
            counter += (input + 1) % 2;
            input = scanner.nextInt();
        }
        System.out.println(counter);
    }
}
