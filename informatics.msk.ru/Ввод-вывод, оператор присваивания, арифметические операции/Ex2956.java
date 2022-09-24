import java.util.Scanner;

public class Ex2956 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int diff1 = n / 1000 - n % 10;
        int diff2 = n % 1000 / 100 - n % 100 / 10;

        diff1 *= diff1;
        diff2 *= diff2;
        System.out.println(1 + diff1 + diff2);
    }
}
