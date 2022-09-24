import java.util.Scanner;

public class Ex2955 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int h = scanner.nextInt(), a = scanner.nextInt(), b = scanner.nextInt();
        int inOneDay = a >= h ? 1 : 0;
        System.out.println(
                inOneDay +
                (1 - inOneDay) * (1 + 1 + (h - a - 1) / (a - b))
        );
    }
}
