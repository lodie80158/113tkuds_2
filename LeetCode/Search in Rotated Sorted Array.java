class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return mid;

            // 判斷左半段是否有序
            if (nums[left] <= nums[mid]) {
                // target 在左半段
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } 
            // 右半段有序
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}

/*
解題思路：
1. 初始化 left, right 指向陣列首尾。
2. 找 mid，若 nums[mid] == target 直接返回。
3. 判斷哪一半是有序的：
   - 如果左半段有序，檢查 target 是否在左半段，是則 right = mid-1，否則 left = mid+1。
   - 如果右半段有序，檢查 target 是否在右半段，是則 left = mid+1，否則 right = mid-1。
4. 循環直到找到或 left > right。
*/
