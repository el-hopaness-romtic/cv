import java.util.Scanner;

public class Ex3065 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt(), s = input;
        while (input != 0) {
            input = scanner.nextInt();
            s += input;
        }
        System.out.println(s);
    }
}
