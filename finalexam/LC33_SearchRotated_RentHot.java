package finalexam;

import java.util.*;

public class LC33_SearchRotated_RentHot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        System.out.println(search(nums, target));
    }

    static int search(int[] nums, int t) {
        int l = 0, r = nums.length-1;
        while (l <= r) {
            int m = (l+r)/2;
            if (nums[m] == t) return m;
            if (nums[l] <= nums[m]) {
                if (nums[l] <= t && t < nums[m]) r = m-1;
                else l = m+1;
            } else {
                if (nums[m] < t && t <= nums[r]) l = m+1;
                else r = m-1;
            }
        }
        return -1;
    }
}
