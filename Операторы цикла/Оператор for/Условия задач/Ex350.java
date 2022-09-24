import java.util.Scanner;

public class Ex350 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt(), c = scanner.nextInt(), d = scanner.nextInt(), e = scanner.nextInt();

        int counter = 0;
        for (int x = 1000; x >= 0; x--) {
            int s = 0, xCurrent = x;
            s += c * xCurrent;
            xCurrent *= x;
            s += b * xCurrent;
            xCurrent *= x;
            s += a * xCurrent;

            if (s == -d) {
                if (x != e) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }

}
