import java.util.Scanner;

public class Ex343 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(), s = 0;
        for (int i = 0; i < n; i++)
            s += scanner.nextInt();

        System.out.print(s);
    }

}
