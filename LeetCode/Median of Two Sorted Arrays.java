class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 確保 nums1 是較短的陣列
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int totalLeft = (m + n + 1) / 2;

        int left = 0, right = m;  // 在 nums1 上二分搜尋
        while (left <= right) {
            int i = (left + right) / 2;   // nums1 的切分點
            int j = totalLeft - i;        // nums2 的切分點

            int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                // 找到正確分割
                if ((m + n) % 2 == 1) {
                    return Math.max(nums1LeftMax, nums2LeftMax);
                } else {
                    return (Math.max(nums1LeftMax, nums2LeftMax) + 
                            Math.min(nums1RightMin, nums2RightMin)) / 2.0;
                }
            } else if (nums1LeftMax > nums2RightMin) {
                right = i - 1; // i 太大，往左
            } else {
                left = i + 1; // i 太小，往右
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted!");
    }
}
