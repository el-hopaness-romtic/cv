import java.util.Scanner;

public class Ex2945 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int nextEven = k + k % 2 + 2 * ((k + 1) % 2);
        System.out.print(nextEven);
    }
}
