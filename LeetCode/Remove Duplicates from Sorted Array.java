class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int uniquePos = 0; // 指向最後一個唯一元素的位置

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[uniquePos]) {
                uniquePos++;             // 移動到下一個唯一元素位置
                nums[uniquePos] = nums[i]; // 更新唯一元素
            }
        }

        return uniquePos + 1; // 長度 = 最後唯一元素位置 + 1
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 原地移動唯一元素到陣列前方，返回唯一元素數量。
   - 陣列已排序，重複元素必相鄰。

2. 解法設計（雙指標法）：
   - uniquePos 指向目前最後一個唯一元素。
   - i 指向當前檢查元素。
   - 如果 nums[i] != nums[uniquePos] → 發現新唯一元素：
       1. uniquePos++
       2. nums[uniquePos] = nums[i]
   - 遍歷完所有元素即可。

3. 範例：
   - nums = [0,0,1,1,1,2,2,3,3,4]
   - 遍歷後 nums = [0,1,2,3,4,...]
   - 返回 5

4. 為什麼可行：
   - 陣列已排序 → 重複元素一定連續 → 用雙指標即可原地處理。
   - 空間 O(1)，時間 O(n)。

時間複雜度：O(n)  
空間複雜度：O(1)
*/
