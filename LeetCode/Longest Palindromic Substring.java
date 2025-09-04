class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return ""; // 空字串直接返回空

        int start = 0, end = 0; // 記錄回文子串的起止索引

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenter(s, i, i);     // 以 i 為中心的奇數長回文
            int len2 = expandFromCenter(s, i, i + 1); // 以 i 和 i+1 為中心的偶數長回文
            int len = Math.max(len1, len2);           // 取較長的回文

            // 更新最長回文的索引範圍
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1); // 返回最長回文子串
    }

    // 從中心擴展，找最大回文長度
    private int expandFromCenter(String s, int left, int right) {
        // 當左右指標在邊界內且字符相同時，繼續擴展
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // 回文長度 = 擴展後的右-左-1
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 找出字串中最長的回文子串。

2. 解法設計（中心擴展法）：
   - 回文的中心可以是：
       1. 單個字母（奇數長回文）
       2. 兩個字母之間（偶數長回文）
   - 對每個字符 i：
       - len1 = 以 i 為中心擴展的奇數回文長度
       - len2 = 以 i 和 i+1 為中心擴展的偶數回文長度
       - 取最大 len，更新最長回文索引範圍

3. 範例：
   - s = "babad"
   - i=0 → len1=1, len2=0 → 最大回文 = "b"
   - i=1 → len1=3, len2=0 → 最大回文 = "bab"
   - i=2 → len1=3 → 回文 = "aba"（和之前長度一樣）
   - 最終返回 "bab" 或 "aba"

4. 為什麼可行：
   - 每個回文都有一個中心，從中心擴展可以找到最大回文。
   - 不需暴力枚舉所有子串，時間複雜度大幅下降。

時間複雜度：O(n^2)  
- 對每個字符擴展最多 n 次 → n * n

空間複雜度：O(1)  
- 只使用固定變數存索引，沒有額外空間
*/
