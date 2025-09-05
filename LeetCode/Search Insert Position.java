class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid; // 找到 target
            } else if (nums[mid] < target) {
                left = mid + 1; // 往右找
            } else {
                right = mid - 1; // 往左找
            }
        }
        // target 不存在，left 就是應該插入的位置
        return left;
    }
}

/*
解題思路：
1. 用二分搜尋檢查 target 是否存在。
2. 找不到時，left 會指向第一個大於 target 的位置，即插入位置。
3. 時間複雜度 O(log n)，空間 O(1)。
*/
