import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Scanner;

import static java.util.Map.Entry;

public class Ex1461 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int prev = -1, counter = 0, lpos, rpos = -1, length = -1;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
            if (prev == arr[i]) {
                counter++;
                if (counter > 2) {
                    rpos = i;
                    length = counter;
                }
            } else {
                prev = arr[i];
                counter = 1;
            }
        }
        if (length == -1) {
            System.out.println(0);
            return;
        }

        counter = length;
        lpos = rpos - (length - 1);
        Entry<Integer, Integer> left = countSameColored(arr, lpos - 1, -1),
                                right = countSameColored(arr, rpos + 1, 1);
        while (left.getKey() == right.getKey() && left.getValue() + right.getValue() > 2) {
            counter += left.getValue() + right.getValue();
            lpos -= left.getValue();
            rpos += right.getValue();

            left = countSameColored(arr, lpos - 1, -1);
            right = countSameColored(arr, rpos + 1, 1);
        }

        System.out.println(counter);
    }

    private static Entry<Integer, Integer> countSameColored(int[] arr, int pos, int step) {
        int key, val = 0;
        if (0 <= pos && pos < arr.length) {
            key = arr[pos];
            for (int i = pos; 0 <= i && i < arr.length && key == arr[i]; i += step) {
                val++;
            }
        } else {
            key = pos;
        }
        return new SimpleImmutableEntry<>(key, val);
    }
}
