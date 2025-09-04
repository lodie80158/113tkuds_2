import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> seen = new HashMap<>(); // 儲存數字與索引對應 {數字: 索引}
        
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i]; // 計算差值，看是否存在已處理過的數字
            if (seen.containsKey(diff)) {
                // 找到一組符合條件的索引 → 返回結果
                return new int[] { seen.get(diff), i };
            }
            seen.put(nums[i], i); // 將當前數字加入 HashMap
        }
        
        return new int[] {}; // 理論上不會走到這裡（題目保證有解）
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 找到數組中兩個數字，使其和為 target，返回索引。

2. 解法設計（哈希表）：
   - 遍歷陣列 nums。
   - 對於每個 nums[i]，計算 diff = target - nums[i]。
   - 查詢 diff 是否已存在於 HashMap：
       - 如果存在 → 找到結果 → 返回索引
       - 如果不存在 → 將 nums[i] 與其索引存入 HashMap
   - 單遍歷完成即可找到答案，時間 O(n)。

3. 範例：
   - nums = [2,7,11,15], target = 9
   - i=0, nums[0]=2, diff=7 → HashMap空
   - i=1, nums[1]=7, diff=2 → HashMap中有2 → 返回 [0,1]

4. 為什麼可行：
   - 哈希表可在 O(1) 查詢差值，保證單次遍歷完成。
   - 空間換時間，時間複雜度 O(n)，空間 O(n)

時間複雜度：O(n)  
空間複雜度：O(n)
*/
