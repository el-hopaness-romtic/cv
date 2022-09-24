import java.util.Scanner;

public class Ex3073 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean alerted = false;
        int input = scanner.nextInt(), s = input;
        while (!alerted || input != 0) {
            alerted = input == 0;
            input = scanner.nextInt();
            s += input;
        }
        System.out.println(s);
    }
}
