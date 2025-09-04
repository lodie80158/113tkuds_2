class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1; // 左右指標
        int maxArea = 0; // 記錄目前最大面積

        // 左右指標相遇前一直計算
        while (left < right) {
            int h = Math.min(height[left], height[right]); // 高度取較小者
            int w = right - left; // 寬度 = 指標距離
            maxArea = Math.max(maxArea, h * w); // 更新最大面積

            // 移動指標：總是移動高度較小的一邊
            if (height[left] < height[right]) {
                left++; // 左邊矮 → 移動左指標希望找到更高柱子
            } else {
                right--; // 右邊矮 → 移動右指標
            }
        }

        return maxArea;
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 給定陣列 height，每個元素代表柱子的高度。
   - 找兩根柱子 i、j，使得面積 (j - i) * min(height[i], height[j]) 最大。

2. 解法設計（雙指標 Two Pointers）：
   - 初始左右指標分別在陣列兩端。
   - 計算當前面積，更新 maxArea。
   - 移動較矮的指標：
       - 原因：面積受限於較矮的一邊，移動較高的一邊不會增加面積。
       - 移動較矮的一邊，有機會遇到更高柱子增加面積。
   - 直到左右指標相遇。

3. 範例：
   - height = [1,8,6,2,5,4,8,3,7]
   - 左右指標最初 1 與 7 → 面積 = 1 * 8 = 8
   - 移動左邊 1 → 左邊 8 與右邊 7 → 面積 = 8*7=56 → 更新 maxArea
   - 持續移動，最終最大面積 = 49 (由柱子 8 與 7 形成)

4. 為什麼可行：
   - 雙指標策略保證每個可能組合被考慮，且每次移動都是有可能增加最大面積。
   - 不需暴力枚舉所有 i,j → O(n)。

時間複雜度：O(n)  
空間複雜度：O(1)
*/
