import java.util.Scanner;

public class Ex3079 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input1 = scanner.nextInt(), input2 = scanner.nextInt();
        if (input2 == 0) {
            System.out.println(0);
            return;
        }
        int input3 = scanner.nextInt(), counter = 0;

        while (input3 != 0) {
            if (input1 < input2 && input2 > input3)
                counter++;

            input1 = input2;
            input2 = input3;
            input3 = scanner.nextInt();
        }

        System.out.println(counter);
    }
}
