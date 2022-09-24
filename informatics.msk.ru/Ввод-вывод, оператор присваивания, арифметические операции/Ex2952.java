import java.util.Scanner;

public class Ex2952 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int firstTimestamp = scanner.nextInt() * 60 * 60 + scanner.nextInt() * 60 + scanner.nextInt(),
                secondTimestamp = scanner.nextInt() * 60 * 60 + scanner.nextInt() * 60 + scanner.nextInt();

        System.out.println(secondTimestamp - firstTimestamp);
    }
}
