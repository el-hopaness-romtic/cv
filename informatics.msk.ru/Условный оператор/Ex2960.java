import java.util.Scanner;

public class Ex2960 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int system = scanner.nextInt(), pupil = scanner.nextInt();

        System.out.println((system == pupil || system != 1 && pupil != 1) ? "YES" : "NO");
    }

}
