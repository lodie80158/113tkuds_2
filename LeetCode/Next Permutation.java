class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // 1. 從右往左找第一個遞減的數
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            // 2. 從右側找第一個比 nums[i] 大的數，交換
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // 3. 反轉 i+1 到末尾
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}

/*
解題思路：
1. 找到右側第一個遞減的元素 nums[i]。
2. 找到右側第一個比 nums[i] 大的元素 nums[j]，交換 i 與 j。
3. 將 i 右側全部反轉，得到下一個字典序排列。
4. 若整個陣列是遞減的，直接反轉整個陣列得到最小排列。
時間複雜度 O(n)，空間 O(1)。
*/
