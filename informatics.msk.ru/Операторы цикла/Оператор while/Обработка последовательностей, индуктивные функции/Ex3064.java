import java.util.Scanner;

public class Ex3064 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int counter = 0, input = scanner.nextInt();
        while (input != 0) {
            counter++;
            input = scanner.nextInt();
        }
        System.out.println(counter);
    }
}
