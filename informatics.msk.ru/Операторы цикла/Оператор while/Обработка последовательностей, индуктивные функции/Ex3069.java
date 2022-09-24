import java.util.Scanner;

public class Ex3069 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt(), counter = 0;
        while (input != 0) {
            int next = scanner.nextInt();
            if (next > input)
                counter++
                        ;
            input = next;
        }
        System.out.println(counter);
    }
}
