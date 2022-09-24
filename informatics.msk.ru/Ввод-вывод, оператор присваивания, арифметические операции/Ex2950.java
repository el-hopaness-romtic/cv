import java.util.Scanner;

public class Ex2950 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int longBreaks = n / 2, shortBreaks = longBreaks + n % 2;
        int isLastBreakShort = n % 2;
        int total = 9 * 60
                + n * 45
                + ((longBreaks - (1 - isLastBreakShort)) * 15 + (shortBreaks - isLastBreakShort) * 5);
        System.out.println(total / 60 + " " + total % 60);
    }
}
