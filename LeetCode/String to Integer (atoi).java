class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;

        int i = 0, n = s.length();
        // 1. 去掉前導空白
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 如果全是空格
        if (i == n) return 0;

        // 2. 判斷正負號
        int sign = 1;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 3. 逐位轉換數字
        long res = 0; // 用 long 暫存避免溢位
        while (i < n && Character.isDigit(s.charAt(i))) {
            res = res * 10 + (s.charAt(i) - '0');

            // 4. 檢查溢位
            if (sign == 1 && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && -res < Integer.MIN_VALUE) return Integer.MIN_VALUE;

            i++;
        }

        return (int)(res * sign);
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 將字串轉為 32 位有號整數，需忽略前導空格，考慮符號，遇非數字停止。
   - 若轉換結果溢位 → 返回 INT_MAX 或 INT_MIN。

2. 解法設計：
   - 去掉前導空格。
   - 判斷正負號，設 sign。
   - 從當前索引開始，逐位轉換數字：
       - res = res * 10 + digit
       - 每次檢查溢位（使用 long 避免臨時溢位）
   - 遇到非數字 → 停止。
   - 返回 res * sign

3. 範例：
   - s = "42" → 42
   - s = "   -42" → -42
   - s = "4193 with words" → 4193
   - s = "-91283472332" → -2147483648 (溢位)

4. 為什麼可行：
   - 逐位處理字元，正負號和溢位檢查完整。
   - 不使用額外字串操作。

時間複雜度：O(n)  
空間複雜度：O(1)
*/
