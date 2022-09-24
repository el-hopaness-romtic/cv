import java.util.Scanner;

public class Ex2944 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        System.out.print(k / 100 + k % 100 / 10 + k % 10);
    }
}
