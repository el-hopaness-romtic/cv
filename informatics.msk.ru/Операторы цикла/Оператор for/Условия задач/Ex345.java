import java.util.Scanner;

public class Ex345 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(), counter = 0;
        for (int i = 0; i < n; i++)
            counter += scanner.nextInt() == 0 ? 1 : 0;

        System.out.print(counter);
    }

}
