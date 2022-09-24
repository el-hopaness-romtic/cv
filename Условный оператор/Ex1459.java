import java.util.Scanner;

public class Ex1459 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        String res = "";
        if (x == 100) {
            res += "C";
            x -= 100;
        }
        if (x >= 90) {
            res += "XC";
            x -= 90;
        }
        if (x >= 50) {
            res += "L";
            x -= 50;
        }
        if (x >= 40) {
            res += "XL";
            x -= 40;
        }
        if (x >= 10) {
            res += "X";
            x -= 10;
            if (x >= 10) {
                res += "X";
                x -= 10;
                if (x >= 10) {
                    res += "X";
                    x -= 10;
                }
            }
        }
        if (x >= 9) {
            res += "IX";
            x -= 9;
        }
        if (x >= 5) {
            res += "V";
            x -= 5;
        }
        if (x >= 4) {
            res += "IV";
            x -= 4;
        }
        if (x >= 1) {
            res += "I";
            x -= 1;
            if (x >= 1) {
                res += "I";
                x -= 1;
                if (x == 1) {
                    res += "I";
                }
            }
        }

        System.out.println(res);
    }
}
