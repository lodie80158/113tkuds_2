import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if (n < 4) return res; // 不足 4 個數，直接回傳空

        Arrays.sort(nums); // 排序，方便用雙指標

        // 第一層迴圈固定 nums[i]
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 避免重複

            // 第二層迴圈固定 nums[j]
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // 避免重複

                int left = j + 1, right = n - 1;

                // 內層雙指標搜尋
                while (left < right) {
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right]; 
                    // 用 long 避免溢位

                    if (sum == target) {
                        // 找到一組解，加入結果
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // 跳過重複值，避免相同組合重複出現
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++; // 總和太小 → 左指標右移
                    } else {
                        right--; // 總和太大 → 右指標左移
                    }
                }
            }
        }

        return res;
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 找出所有不重複的四元組 (a, b, c, d)，使得 a+b+c+d = target。

2. 解法設計（排序 + 雙指標延伸）：
   - 類似 3Sum 的解法，但多一層迴圈固定第二個數。
   - 步驟：
       (1) 排序陣列，方便控制和的大小。
       (2) 外層兩個迴圈，固定 nums[i] 和 nums[j]。
       (3) 內層用雙指標 left 和 right，搜尋另外兩個數。
       (4) 判斷總和 sum：
           - 若 sum == target → 記錄解，並跳過重複數字。
           - 若 sum < target → 移動 left，使總和變大。
           - 若 sum > target → 移動 right，使總和變小。
       (5) 注意去重：i 和 j 需跳過相同值，left 和 right 也需跳過相同值。

3. 範例：
   - nums = [1,0,-1,0,-2,2], target = 0
   - 排序後 = [-2,-1,0,0,1,2]
   - 找到解：[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]

4. 為什麼可行：
   - 排序 + 雙指標的特性可讓搜尋空間大幅縮小。
   - 使用去重策略避免重複解。

時間複雜度：O(n^3)  
- 排序 O(n log n)，兩層迴圈 O(n^2)，內層雙指標 O(n)，總計 O(n^3)。  

空間複雜度：O(1)  
- 除了輸出結果，幾乎不需要額外空間。
*/
