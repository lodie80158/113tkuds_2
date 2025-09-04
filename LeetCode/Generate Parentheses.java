import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    // 回溯函數
    private void backtrack(List<String> res, StringBuilder path, int open, int close, int max) {
        // 當括號數量達到 n，加入結果
        if (path.length() == max * 2) {
            res.add(path.toString());
            return;
        }

        // 還可以加開括號
        if (open < max) {
            path.append('(');
            backtrack(res, path, open + 1, close, max);
            path.deleteCharAt(path.length() - 1); // 回溯
        }

        // 還可以加閉括號 (閉括號數 < 開括號數)
        if (close < open) {
            path.append(')');
            backtrack(res, path, open, close + 1, max);
            path.deleteCharAt(path.length() - 1); // 回溯
        }
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 生成 n 對括號的所有合法組合。
   - 合法：任意前綴中 '(' >= ')'

2. 解法設計（回溯）：
   - 用 path 儲存當前組合。
   - open 記錄已放入 '(' 數量，close 記錄已放入 ')' 數量。
   - 條件：
       1. open < n → 可以加 '('
       2. close < open → 可以加 ')'
   - 遞迴探索所有可能，回溯刪除最後一個字符，繼續嘗試。

3. 範例：
   - n = 3 → 遞迴生成：
     "((()))","(()())","(())()","()(())","()()()"

4. 為什麼可行：
   - 回溯可完整枚舉所有合法組合，避免非法括號。
   - 單次遞迴生成，每個組合長度 2n。

時間複雜度：O(4^n / sqrt(n)) (Catalan 數)  
空間複雜度：O(n) (遞迴深度)
*/
