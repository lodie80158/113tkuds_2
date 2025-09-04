class Solution {
    public String longestCommonPrefix(String[] strs) {
        // 邊界條件：如果陣列為空或長度為 0，直接回傳空字串
        if (strs == null || strs.length == 0) return "";

        // 先假設第一個字串是共同前綴
        String prefix = strs[0];

        // 從第二個字串開始檢查
        for (int i = 1; i < strs.length; i++) {
            // 當前字串不以 prefix 開頭時，不斷縮短 prefix
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1); // 去掉最後一個字元
                if (prefix.isEmpty()) return ""; // 如果縮短到空字串，直接回傳
            }
        }

        return prefix; // 最後留下的就是最長共同前綴
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 找出所有字串的「最長共同前綴」。
   - 如果沒有共同前綴，回傳空字串。

2. 解法設計：
   - 假設第一個字串是 prefix。
   - 從第二個字串開始檢查：
       - 若當前字串不是以 prefix 開頭，就逐步縮短 prefix。
       - 縮短方式：每次去掉最後一個字元，直到符合「startsWith(prefix)」為止。
   - 若縮短到空字串，代表沒有共同前綴，回傳 ""。

3. 為什麼可行：
   - prefix 只會越來越短，不會變長。
   - 一旦所有字串都檢查過，剩下的 prefix 一定是所有字串的共同前綴。

4. 範例：
   - strs = ["flower","flow","flight"]
   - prefix = "flower"
   - 與 "flow" 比對 → prefix 變成 "flow"
   - 與 "flight" 比對 → prefix 變成 "fl"
   - 最後答案 = "fl"

時間複雜度：O(S)  
- S = 所有字串的總字元數，因為每個字元最多被比較一次。

空間複雜度：O(1)  
- 只用了額外的變數 prefix。
*/
