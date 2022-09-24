import java.util.Scanner;

public class Ex3058 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int smallestFactor = 1;
        while (smallestFactor < n / smallestFactor + 1) {
            smallestFactor++;
            if (n % smallestFactor == 0) {
                System.out.println(smallestFactor);
                return;
            }
        }
        System.out.println(n);
    }
}
