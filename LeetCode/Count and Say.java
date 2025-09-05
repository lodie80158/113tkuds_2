class Solution {
    public String countAndSay(int n) {
        String res = "1"; // 初始化序列，第 1 個 count-and-say 字串為 "1"
        
        // 從第 2 個開始生成，直到第 n 個
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder(); // 用來構建新的字串
            int count = 1; // 記錄連續相同數字的個數
            
            // 從第 2 個字元開始遍歷上一個字串
            for (int j = 1; j < res.length(); j++) {
                if (res.charAt(j) == res.charAt(j - 1)) {
                    count++; // 如果跟前一個字元相同，計數 +1
                } else {
                    // 遇到不同數字，把前一段連續數字的「數量+數字」加入 sb
                    sb.append(count).append(res.charAt(j - 1));
                    count = 1; // 重置計數器
                }
            }
            
            // 處理最後一段連續數字
            sb.append(count).append(res.charAt(res.length() - 1));
            
            res = sb.toString(); // 更新 res 為本輪生成的字串
        }
        
        return res; // 返回第 n 個 count-and-say 字串
    }
}

/*
解題思路：
1. count-and-say 序列是一種「讀上一層數字的出現次數，生成下一層字串」的遞迴規則。
   - 例如：1 → "11" → "21" → "1211" → "111221" …
2. 迴圈生成第 2 到第 n 個字串：
   - 用 StringBuilder 來累積下一個字串
   - 使用 count 記錄連續相同數字的出現次數
   - 每遇到不同數字，將「count + 數字」加入 sb
   - 特別處理最後一段，避免漏掉
3. 返回最終生成的字串即為答案
時間複雜度：O(n * m)，其中 m 為第 n 個字串的長度
空間複雜度：O(m)，用於儲存當前生成的字串
*/
