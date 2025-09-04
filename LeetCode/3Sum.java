import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // 排序，方便用雙指標處理
        int n = nums.length;

        // 固定第一個數 nums[i]
        for (int i = 0; i < n - 2; i++) {
            // 若當前數字與前一個數字相同，跳過避免重複解
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1, right = n - 1;

            // 雙指標搜尋另外兩個數
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // 找到解，加入結果
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳過重複的數字，避免相同解重複出現
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;  // 總和太小 → 左指標右移
                } else {
                    right--; // 總和太大 → 右指標左移
                }
            }
        }

        return res;
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 找出所有不重複的三元組 (a, b, c)，使得 a + b + c = 0。

2. 解法設計（排序 + 雙指標）：
   - 先對陣列排序，方便控制總和大小。
   - 外層固定第一個數 nums[i]。
   - 內層使用雙指標 left = i+1, right = n-1。
   - 計算 sum = nums[i] + nums[left] + nums[right]：
       - 若 sum == 0 → 記錄解，並跳過重複數字。
       - 若 sum < 0 → 移動 left，使總和變大。
       - 若 sum > 0 → 移動 right，使總和變小。
   - 注意：需要處理重複解，因此在找到一組解後，必須跳過相同數字。

3. 範例：
   - nums = [-1,0,1,2,-1,-4]
   - 排序後 = [-4,-1,-1,0,1,2]
   - i=0 → nums[i]=-4，不可能湊 0（因為後面最小和 > -4），結果空
   - i=1 → nums[i]=-1，找到 [-1,-1,2], [-1,0,1]
   - i=2 → nums[i]=-1（跳過，避免重複）
   - 最後結果 = [[-1,-1,2], [-1,0,1]]

4. 為什麼可行：
   - 排序後能保證雙指標能透過移動縮小搜尋空間。
   - 利用排序和跳過重複元素，能避免相同三元組重複。

時間複雜度：O(n^2)  
- 排序 O(n log n)，外層 O(n)，內層雙指標 O(n)。總計 O(n^2)。

空間複雜度：O(1)  
- 除了輸出結果外，僅使用少數變數。
*/
