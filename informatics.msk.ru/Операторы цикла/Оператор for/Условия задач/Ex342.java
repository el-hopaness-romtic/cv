import java.util.Scanner;

public class Ex342 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int s = 0;
        for (int i = 0; i < 100; i++)
            s += scanner.nextInt();

        System.out.print(s);
    }

}
