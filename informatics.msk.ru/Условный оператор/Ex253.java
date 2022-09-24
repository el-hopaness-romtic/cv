import java.util.Scanner;

public class Ex253 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        System.out.println(year % 4 == 0 && year % 100 != 0 || year % 400 == 0 ? "YES" : "NO");
    }

}
