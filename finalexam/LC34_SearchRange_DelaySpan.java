package finalexam;

import java.util.*;

public class LC34_SearchRange_DelaySpan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        int[] res = searchRange(nums, target);
        System.out.println(res[0] + " " + res[1]);
    }

    static int[] searchRange(int[] nums, int target) {
        int left = lower(nums, target);
        if (left == nums.length || nums[left] != target) return new int[]{-1,-1};
        int right = lower(nums, target+1) - 1;
        return new int[]{left, right};
    }

    static int lower(int[] a, int t) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = (l+r)/2;
            if (a[m] < t) l = m+1; else r = m;
        }
        return l;
    }
}

