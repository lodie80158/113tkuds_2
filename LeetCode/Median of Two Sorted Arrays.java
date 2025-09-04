class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 保證 nums1 是較短的陣列，減少二分搜尋範圍
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int totalLeft = (m + n + 1) / 2; // 左半部分元素數量

        int left = 0, right = m;  // 在 nums1 上做二分搜尋
        while (left <= right) {
            int i = (left + right) / 2;   // nums1 的切分點
            int j = totalLeft - i;        // nums2 的切分點

            // nums1 左右最大最小
            int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];

            // nums2 左右最大最小
            int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // 正確切分條件
            if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                // 奇數長 → 左半最大即中位數
                if ((m + n) % 2 == 1) {
                    return Math.max(nums1LeftMax, nums2LeftMax);
                } else {
                    // 偶數長 → 左半最大與右半最小平均
                    return (Math.max(nums1LeftMax, nums2LeftMax) + 
                            Math.min(nums1RightMin, nums2RightMin)) / 2.0;
                }
            } else if (nums1LeftMax > nums2RightMin) {
                right = i - 1; // i 太大，向左移
            } else {
                left = i + 1; // i 太小，向右移
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted!");
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 找兩個已排序陣列合併後的中位數，但不能直接合併 → 要 O(log(min(m,n))) 時間。

2. 解法設計（二分搜尋 + 切分）：
   - 將兩個陣列切分為左右兩半：
       - 左半部分元素總數 = (m+n+1)/2
       - 左半最大值 ≤ 右半最小值 → 找到正確切分
   - 在較短陣列上二分搜尋切分點 i，另一陣列 j = totalLeft - i。
   - 判斷 nums1[i-1] 和 nums2[j-1] 是否滿足條件：
       1. nums1LeftMax ≤ nums2RightMin && nums2LeftMax ≤ nums1RightMin → 切分正確
       2. nums1LeftMax > nums2RightMin → i 太大 → 移右指標
       3. nums2LeftMax > nums1RightMin → i 太小 → 移左指標
   - 最終返回奇數長中位數或偶數長平均值。

3. 範例：
   - nums1 = [1,3], nums2 = [2]
   - totalLeft = 2
   - 切分 i=1 → j=1 → 左半 = [1,2], 右半 = [3] → 中位數 = 2

4. 為什麼可行：
   - 利用二分縮小搜尋空間，直接找到正確切分，不需合併。
   - 保證時間複雜度 O(log(min(m,n)))。

時間複雜度：O(log(min(m,n)))  
空間複雜度：O(1)
*/
