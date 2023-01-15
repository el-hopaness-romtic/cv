import java.util.Scanner;

public class Ex304 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int ticket60, ticket10 = 0, ticket1 = 0;
        ticket60 = n / 60;
        n %= 60;
        if (n > 34) {
            ticket60++;
        } else {
            ticket10 = n / 10;
            n %= 10;
            if (n > 8)
                ticket10++;
            else
                ticket1 = n;
        }

        System.out.println(ticket1 + " " + ticket10 + " " + ticket60);
    }
}
