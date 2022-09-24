import java.util.Scanner;

public class Ex2961 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(), b = scanner.nextInt(), c = scanner.nextInt();
        int[] nums = new int[]{a, b, c};

        int imax = 0, imin = 1;
        if (nums[imin] > nums[0])
            imin = 0;
        if (nums[imin] > nums[2])
            imin = 2;
        if (nums[imax] < nums[1])
            imax = 1;
        if (nums[imax] < nums[2])
            imax = 2;

        System.out.println(nums[imin] + " " + nums[3 - imin - imax] + " " + nums[imax]);
    }
}
