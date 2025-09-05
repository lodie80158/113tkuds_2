class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = extremeInsertionIndex(nums, target, true);
        // 檢查 target 是否存在
        if (left == nums.length || nums[left] != target) {
            return new int[] {-1, -1};
        }
        int right = extremeInsertionIndex(nums, target, false) - 1;
        return new int[] {left, right};
    }

    // flag = true -> 找左邊界, false -> 找右邊界
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target || (left && nums[mid] == target)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}

/*
解題思路：
1. 用二分搜尋找到 target 的左邊界（第一次出現的位置）。
2. 如果左邊界不等於 target，表示陣列中沒有 target，返回 [-1,-1]。
3. 再用二分搜尋找到右邊界（最後一次出現的位置），注意要 -1。
4. 返回 [left, right]。
*/
