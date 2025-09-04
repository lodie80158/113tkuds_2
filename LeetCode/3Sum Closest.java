import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 先排序，方便雙指標移動
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2]; // 先假設前三個數的和為初始最接近值

        // 固定第一個數 nums[i]，再用雙指標搜尋
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;

            // 雙指標向中間靠攏
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                // 如果 sum 剛好等於 target，這就是最接近答案
                if (sum == target) return sum;

                // 更新目前找到的最接近值
                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }

                // 調整指標：根據 sum 與 target 的大小關係移動
                if (sum < target) {
                    left++;  // 和太小 → 移動左指標增加總和
                } else {
                    right--; // 和太大 → 移動右指標減少總和
                }
            }
        }

        return closestSum; // 回傳最接近 target 的總和
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 從陣列中挑選三個數，使其和最接近 target。
   - 如果存在多組候選，選任意最接近的一組。

2. 解法設計（排序 + 雙指標 Two Pointers）：
   - 先對陣列排序，因為排序後能使用雙指標快速調整和的大小。
   - 外層固定一個數 nums[i]。
   - 內層用兩個指標 left 和 right，分別指向 i+1 與 n-1。
   - 每次計算 sum = nums[i] + nums[left] + nums[right]：
       - 若 sum == target → 最佳答案，直接回傳。
       - 若 sum 更接近 target，更新 closestSum。
       - 若 sum < target → 移動 left（使總和變大）。
       - 若 sum > target → 移動 right（使總和變小）。

3. 範例：
   - nums = [-1, 2, 1, -4], target = 1
   - 排序後 = [-4, -1, 1, 2]
   - i=0 → [-4, -1, 2] = -3 → closest=-3
   - 持續調整 → 找到 [ -1, 1, 2 ] = 2，距離 target=1 最近
   - 最終答案 = 2

4. 為什麼可行：
   - 雙指標在排序陣列中保證可以有效縮小搜尋空間。
   - 每次調整 left 或 right 都能讓 sum 更靠近 target。

時間複雜度：O(n^2)  
- 排序 O(n log n)，外層迴圈 O(n)，內層雙指標 O(n)。  
- 總計 O(n^2)。

空間複雜度：O(1)  
- 僅使用額外變數，排序可視為原地操作。
*/
