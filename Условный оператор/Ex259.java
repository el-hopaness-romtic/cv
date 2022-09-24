import java.util.Scanner;

public class Ex259 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();

        if (k == 1)
            System.out.println("YES");
        else
            System.out.println(k % 4 == 0 ? "YES" : "NO");
    }
}
