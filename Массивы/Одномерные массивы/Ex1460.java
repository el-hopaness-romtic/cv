import java.util.Scanner;

public class Ex1460 {
    private static int nthOccurrence(String str, char c, int n) {
        if (n <= 0) {
            return -1;
        }
        int pos = str.indexOf(c, 0);
        while (n-- > 1 && pos != -1)
            pos = str.indexOf(c, pos + 1);
        return pos;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String nums = scanner.nextLine();
        int k = Integer.parseInt(scanner.nextLine()) % n;

        if (k < 0)
            k += n;
        int pos = nthOccurrence(nums, ' ', n - k);
        System.out.print(nums.substring(pos + 1));
        System.out.print(' ');
        if (pos != -1)
            System.out.println(nums.substring(0, pos));

/*
        for (int i = 0, counter = 0; counter < n; i++) {
            int ind = i, temp = arr[ind], tetemp;
            do {
                ind = (ind + k) % n;

                tetemp = arr[ind];
                arr[ind] = temp;
                temp = tetemp;
                counter++;
            } while (ind != i);
        }

        for (int el : arr) {
            System.out.print(el + " ");
        }

---------------------------------------------------------------------

        for (int i = 0; i < n; i++) {
            System.out.print(arr[(n - k + i) % n] + " ");
        }*/
    }
}
