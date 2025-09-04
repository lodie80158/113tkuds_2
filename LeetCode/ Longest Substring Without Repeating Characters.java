import java.util.HashSet;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>(); // 用來存目前視窗中的字元
        int left = 0, maxLen = 0; // left: 左指標, maxLen: 最長子字串長度

        // right 當作右指標，遍歷整個字串
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // 如果當前字元已經在集合中，代表重複，需要移動左指標直到移除重複
            while (set.contains(c)) {
                set.remove(s.charAt(left)); // 移除左邊的字元
                left++; // 左指標右移
            }

            // 將當前字元加入集合
            set.add(c);

            // 更新最大長度（右指標 - 左指標 + 1）
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 找出字串中最長的「不包含重複字元」的子字串長度。
   - 子字串必須連續。

2. 解法設計（滑動視窗法 Sliding Window）：
   - 使用兩個指標：left 和 right 表示當前視窗範圍。
   - 使用 HashSet 存視窗內的字元，保證不重複。
   - 每次右指標 right 前進一格：
       - 如果出現重複字元，就移動 left 並從 set 移除字元，直到沒有重複為止。
       - 把當前字元加進 set。
       - 更新目前最長長度。

3. 範例：
   - s = "abcabcbb"
   - right=0 → "a" → maxLen=1
   - right=1 → "ab" → maxLen=2
   - right=2 → "abc" → maxLen=3
   - right=3 → 遇到 'a' 重複 → left 移到 1，set={b,c,a} → maxLen=3
   - 最後答案 = 3

4. 為什麼可行：
   - 視窗只會往右移動，不會回退。
   - 每個字元最多被加入和移除一次，因此效率高。

時間複雜度：O(n)  
- n = 字串長度，每個字元最多處理兩次（加入/移除）。

空間複雜度：O(min(n, m))  
- m = 字元集大小（若是 ASCII = 128），存放當前視窗字元。
*/
