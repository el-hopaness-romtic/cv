import java.util.Scanner;

public class Ex3066 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt(), s = input, counter = 0;
        while (input != 0) {
            input = scanner.nextInt();
            s += input;
            counter++;
        }
        System.out.println((double) s / counter);
    }
}
