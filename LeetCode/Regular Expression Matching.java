class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true; // 空字串匹配空 pattern

        // 初始化 pattern 匹配空字串的情況
        // 只有 '*' 可以讓 pattern 匹配空字串（需忽略前一個字元）
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // 填表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == sc || pc == '.') {
                    // 字符匹配或 '.' 匹配任意單字符
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    // '*' 當前一個字元出現 0 次
                    dp[i][j] = dp[i][j - 2];

                    // '*' 當前一個字元出現 ≥1 次
                    char pre = p.charAt(j - 2);
                    if (pre == sc || pre == '.') {
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 給定字串 s 與正則 pattern p（含 '.' 與 '*'），判斷是否完全匹配。
   - '.' 匹配任意單字符，'*' 匹配前一個字符 0 或多次。

2. 解法設計（動態規劃）：
   - dp[i][j] 表示 s[0..i-1] 是否匹配 p[0..j-1]。
   - 狀態轉移：
       1. pc == sc 或 pc == '.' → dp[i][j] = dp[i-1][j-1]
       2. pc == '*' → 考慮兩種情況：
           a. '*' 當 0 個前一字符 → dp[i][j] = dp[i][j-2]
           b. '*' 當 ≥1 個前一字符 → dp[i][j] |= dp[i-1][j] （前一字符匹配 sc 或 '.'）
   - 初始化：
       - dp[0][0] = true（空字串匹配空 pattern）
       - dp[0][j] = dp[0][j-2] if p[j-1] == '*'（空字串能匹配 '*'）

3. 範例：
   - s = "aab", p = "c*a*b"
   - dp 填表後，最後 dp[3][6] = true → 匹配

4. 為什麼可行：
   - 動態規劃考慮每個位置是否匹配，包含 '*' 的 0 或多次情況，保證覆蓋所有可能。
   - 時間複雜度 O(m*n)，空間複雜度 O(m*n)

時間複雜度：O(m*n)  
空間複雜度：O(m*n)
*/
