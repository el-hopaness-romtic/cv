import java.util.Scanner;

public class Ex264 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();

        System.out.println(k < 3 || k == 4 || k == 7 ? "NO" : "YES");
    }
}
