import java.util.Scanner;

public class Ex2958 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt();
        System.out.println(((a / b) * a + (b / a) * b) / ((a / b) + (b / a)));
    }
}
