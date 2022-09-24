import java.util.Scanner;

public class Ex66 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), counter = 0;

        int curr, prev = scanner.nextInt();
        for (int i = 1; i < n; i++) {
            curr = scanner.nextInt();
            if (curr > prev)
                counter++;
            prev = curr;
        }
        System.out.println(counter);
    }
}
