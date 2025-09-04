class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0; // 指向下一個非 val 元素應放的位置

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i]; // 將非 val 元素移到前方
                k++;
            }
        }

        return k; // k 即不等於 val 的元素數量
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 原地移除陣列中所有 val 元素，返回剩餘元素數量。
   - 不要求保持順序，可打亂。

2. 雙指標設計：
   - k 指向下一個應放置非 val 元素的位置。
   - 遍歷 i = 0..nums.length-1：
       - 如果 nums[i] != val → 移到 nums[k]，k++
       - 如果 nums[i] == val → 跳過

3. 範例：
   - nums = [0,1,2,2,3,0,4,2], val = 2
   - 遍歷後 nums = [0,1,3,0,4,...]
   - 返回 k = 5

4. 時間與空間：
   - 時間 O(n)，單次遍歷整個陣列
   - 空間 O(1)，原地修改
*/
