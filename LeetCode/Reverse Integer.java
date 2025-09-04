class Solution {
    public int reverse(int x) {
        int rev = 0; // 儲存反轉後的數字

        while (x != 0) {
            int pop = x % 10; // 取出最低位
            x /= 10;          // 去掉最低位

            // 檢查是否會溢位（int 範圍 [-2^31, 2^31-1]）
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0; // 正溢位
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0; // 負溢位
            }

            rev = rev * 10 + pop; // 將 pop 加到反轉數字末尾
        }

        return rev;
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 將整數反轉，例如 123 → 321，-123 → -321。
   - 若反轉後溢出 32 位有號整數範圍 → 返回 0。

2. 解法設計：
   - 循環取出最低位 pop = x % 10，x /= 10。
   - 將 pop 加到 rev 後面 → rev = rev * 10 + pop。
   - 每次操作前檢查是否會溢位：
       - 正溢位：rev > MAX/10 或 rev==MAX/10 且 pop>7
       - 負溢位：rev < MIN/10 或 rev==MIN/10 且 pop<-8

3. 範例：
   - x = 123 → pop=3,2,1 → rev=321 → 返回 321
   - x = -123 → pop=-3,-2,-1 → rev=-321 → 返回 -321
   - x = 1534236469 → 反轉會溢位 → 返回 0

4. 為什麼可行：
   - 逐位反轉，無需轉字串。
   - 溢位檢查確保安全，O(log10(x)) 時間複雜度。

時間複雜度：O(log10(|x|))  
空間複雜度：O(1)
*/
